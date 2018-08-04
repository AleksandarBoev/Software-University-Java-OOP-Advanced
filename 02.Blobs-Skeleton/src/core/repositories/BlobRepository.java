package core.repositories;

import models.Blob;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlobRepository implements Repository<Blob> {
    private Map<String, Blob> nameBlob;

    public BlobRepository() {
        this.nameBlob = new LinkedHashMap<>();
    }

    public Blob getBlob(String name) {
        return this.nameBlob.get(name);
    }

    @Override
    public void add(Blob blob) {
        String name = blob.getName();
        this.nameBlob.put(name, blob);
    }
}
