package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        //TODO check to repeat
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Резюме " + r.getUuid() + " уже существует");
        } else if (size == storage.length) {
            System.out.println("Место хранения переполнено!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        //TODO check for availability
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Резюме " + r.getUuid() + " не существует");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Такого резюме нет.");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        //TODO check for availability
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Такого резюме нет.");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}