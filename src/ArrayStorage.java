import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                i++;
            } else if (storage[i].uuid.equals(r.uuid)) {
                System.out.println("Такое резюме уже существует");
                return;
            }
        }

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume aStorage : storage) {
            if (aStorage == null) {
                System.out.println("Извините, указаный UUID: " + uuid + "не найден");
                break;
            } else if (aStorage.uuid.equals(uuid)) {
                return aStorage;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                break;
            }
        }

        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) {
                for (int j = i; j < storage.length; j++) {
                    if (storage[j] != null) {
                        storage[i] = storage[j];
                        storage[j] = null;
                        i++;
                    }
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int nullPosition = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                nullPosition = i;
                break;
            }
        }
        return Arrays.copyOf(storage, nullPosition);
    }

    int size() {
        return getAll().length;
    }
}
