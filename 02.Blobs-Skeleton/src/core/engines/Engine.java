package core.engines;

import core.repositories.BlobRepository;
import core.repositories.Repository;
import factories.BlobFactory;
import factories.Factory;
import models.Blob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    Repository<Blob> blobRepository;
    Factory<Blob> blobFactory;

    public Engine() {
        this.blobRepository = new BlobRepository();
        this.blobFactory = new BlobFactory();
    }

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //create Cenko 30 15 Inflated PutridFart
        //create Boko 50 20 Aggressive Blobplode
        //attack Boko Cenko
        //status
        //status
        //status
        //status
        //status
        //status

        String input;
        while(!"drop".equals(input = reader.readLine())) {
            String[] tokens = input.split(" ");

            String command = tokens[0];
            switch (command) {
                case "create":
                    Blob createdBlob = this.blobFactory.create(tokens);
                    this.blobRepository.add(createdBlob);
                    break;

                case "attack":

                    break;

                case "status":

                    break;
            }
        }

        reader.close();
    }
}
