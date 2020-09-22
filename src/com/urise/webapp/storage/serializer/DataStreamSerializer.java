package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            //TODO implements section
        }
//        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
//            Map<SectionType, Section> sectionMap = r.getSections();
//            oos.writeInt(sectionMap.size());
//            for (Map.Entry<SectionType, Section> entry : sectionMap.entrySet()) {
//                oos.writeUTF(entry.getKey().name());
//                oos.writeObject(entry.getValue());
//            }
//        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            //TODO implements section
//            try (ObjectInputStream ois = new ObjectInputStream(is)) {
//                SectionType sectionType = SectionType.valueOf(ois.readUTF());
//                Section section = (Section) ois.readObject();
//                resume.addSection(sectionType, section);
//            } catch (ClassNotFoundException e) {
//                throw new StorageException("Read error");
//            }
            return resume;
        }
    }
}

