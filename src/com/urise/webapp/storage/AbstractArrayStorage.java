package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage,0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        //TODO check to repeat
        int index = getIndex(r.getUuid());
        if (index > 0) {
            System.out.println("Резюме " + r.getUuid() + " уже существует");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Место хранения переполнено!");
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void update(Resume r) {
        //TODO check for availability
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Резюме " + r.getUuid() + " не существует");
        } else {
            storage[index] = r;
        }
    }

    public void delete(String uuid) {
        //TODO check for availability
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Такого резюме нет.");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Такого резюме нет.");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}