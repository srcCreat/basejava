package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private Storage storage;
    private static final String UUID_1 = "uuid_1";
    public static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid_2";
    public static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid_3";
    public static final Resume RESUME_3 = new Resume(UUID_3);

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        Resume[] result = storage.getAll();
        Assert.assertEquals("uuid_1", result[0].getUuid());
        Assert.assertEquals("uuid_2", result[1].getUuid());
        Assert.assertEquals("uuid_3", result[2].getUuid());
    }

    @Test (expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void update() {
        Resume[] result = storage.getAll();
        storage.update(RESUME_1);
        storage.update(RESUME_2);
        storage.update(RESUME_3);
        Assert.assertEquals("uuid_1", result[0].getUuid());
        Assert.assertEquals("uuid_2", result[1].getUuid());
        Assert.assertEquals("uuid_3", result[2].getUuid());
    }

    @Test (expected = NotExistStorageException.class)
    public void updateExist() {
        storage.update(new Resume());
    }

    @Test (expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid_3");
        Assert.assertEquals(2, storage.size());
        storage.get("uud_3");
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get("uuid_1"));
    }

    @Test
    public void getAll() {
        Resume[] result = storage.getAll();
        Assert.assertEquals(3, result.length);
        assertEquals(RESUME_1, result[0]);
        assertEquals(RESUME_2, result[1]);
        assertEquals(RESUME_3, result[2]);
    }

}