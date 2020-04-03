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
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("Такое резюме уже существует");
                return;
            }
        }

        if (size >= storage.length) {
            System.out.println("Место хранения переполнено!");
        }

        //TODO save resume
        if (size == 0) {
            storage[0] = r;
            size++;
        } else {
            for (int i = 1; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = r;
                    size++;
                    break;
                }
            }
        }
    }

    public void update(Resume r) {
        //TODO check for availability
        boolean isAvailable = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) isAvailable = true;
        }
        if (!isAvailable) {
            System.out.println("Такого резюме нет");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].equals(r)) storage[i] = r;
            }
        }
    }

    public Resume get(String uuid) {
        boolean isAvailable = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) isAvailable = true;
        }

        if (!isAvailable) {
            System.out.println("Извините, указаный UUID: " + uuid + " не найден");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        //TODO check for availability
        boolean isAvailable = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) isAvailable = true;
        }

        if (!isAvailable) {
            System.out.println("Такого резюме нет.");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                }
            }
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
}