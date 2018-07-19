package p08_petClinics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Clinic {
    private static final String INVALID_ROOMS_MESSAGE = "Invalid Operation!";

    private String name;
    private List<Room> rooms;

    public Clinic(String name, int numberOfRooms) {
        this.name = name;
        setRooms(numberOfRooms);
    }

    public List<Room> getRooms() {
        return Collections.unmodifiableList(this.rooms);
    }

    private void setRooms(int numberOfRooms) {
        if (numberOfRooms % 2 != 1) {
            throw new IllegalArgumentException(INVALID_ROOMS_MESSAGE);
        }

        this.rooms = new ArrayList<>();
        for (int i = 0; i < numberOfRooms; i++) {
            this.rooms.add(new Room());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Room room : this.rooms) {
            sb.append(room.toString()).append("\n");
        }

        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public boolean addPet(Pet pet) {
        AddIterator iterator = new AddIterator();
        while (iterator.hasNext()) {
            Room currentRoom = iterator.next();
            if (!currentRoom.isOccupied()) { //if room is empty then put the pet there
                currentRoom.setOccupied(true);
                currentRoom.setPet(pet);
                return true;
            }
        }

        return false;
    }
    private final class AddIterator implements Iterator<Room> {
        //The idea: first returned value will be the middle one and the counter will become bigger (because at first iteration this.plus is true).
        //this.plus becomes false after the value is returned. Next iteration: the returned value will be middle - counter(which is 1).
        //Next iteration: middle + counter(which is still 1, but becomes 2). Next iteration: middle - counter(which is 2). And so on...
        private int middle;
        private int counter;

        private boolean plus;

        public AddIterator() {
            this.middle = rooms.size() / 2; // if size = 5 --> [0] [1]>[2]<[3] [4]
            this.counter = 0;
            this.plus = true;
        }

        @Override
        public boolean hasNext() {
            if (this.plus) {
                return (this.middle + this.counter) < rooms.size();
            } else {
                return (this.middle - this.counter) >= 0;
            }
        }
        @Override
        public Room next() {
            Room result = null;
            if (this.plus) {
                result = rooms.get(this.middle + this.counter);
                this.counter++;
            } else {
                result = rooms.get(this.middle - this.counter);
            }

            this.plus = !this.plus; // flip value
            return result;
        }

    }

    public boolean releasePet() {
        ReleaseIterator iterator = new ReleaseIterator();
        while (iterator.hasNext()) {
            Room currentRoom = iterator.next();
            if (currentRoom.isOccupied()) { //if there is a pet in this room, release it
                currentRoom.setOccupied(false);
                currentRoom.setPet(null);
                return true;
            }
        }

        return false;
    }
    private final class ReleaseIterator implements Iterator<Room> {
        private int cursor;
        private int middle;

        private boolean afterMiddle;

        public ReleaseIterator() {
            this.middle = rooms.size() / 2;
            this.cursor = this.middle - 1;
            this.afterMiddle = true;
        }

        @Override
        public boolean hasNext() {
            if (this.afterMiddle) {
                if (this.cursor + 1 < rooms.size()) {
                    return true;
                } else {
                    this.afterMiddle = false;
                    this.cursor = 0;
                    return this.cursor + 1 < rooms.size();
                }
            } else {
                return this.cursor + 1 < this.middle;
            }
        }
        @Override
        public Room next() {
            return rooms.get(++this.cursor);
        }

    }

    public boolean hasEmptyRooms() {
        for (Room room : this.rooms) {
            if (!room.isOccupied()) {
                return true;
            }
        }

        return false;
    }
}
