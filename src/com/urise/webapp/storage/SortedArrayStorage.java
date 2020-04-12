package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
//        if (getIndex(r.getUuid()) != -1) {
//            System.out.println("Резюме " + r.getUuid() + " уже существует");
//        } else
        if (size >= STORAGE_LIMIT) {
            System.out.println("Место хранения переполнено!");
        } else {
//            int position = Math.abs(Arrays.binarySearch(storage, 0, size, r));
//            if ((position-1) > size) {
//                storage[size] = r;
//            } else if ((position-1) < size) {
//                if (storage[position-1] != null) {
//                    for (int i = 0; i < size; i++) {
//                        int value = storage[i].compareTo(r);
//                        int temp = 0;
//                        if (value < 0) {
//                            storage[i+1] = r;
//                        } else if (value > 0) {
//                            storage[i-1] = r;
//                        }
//                    }
//                }
//            }
//            storage[position-1] = r;
//            size++;
        }
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
