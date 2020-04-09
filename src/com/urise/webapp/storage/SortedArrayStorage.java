package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

//    @Override
//    public void clear() {
//
//    }

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Резюме " + r.getUuid() + " уже существует");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Место хранения переполнено!");
        } else {
            for (int i = 0; i < size; i++) {
                if (Integer.parseInt(r.getUuid()) > Integer.parseInt(storage[i].getUuid())) {
                    storage[i] = r;
                }
            }
            storage[size] = r;
            size++;
        }
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
