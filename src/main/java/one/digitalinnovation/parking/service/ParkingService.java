package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id = getUUID(); //Gera uma id aleatoria para o parking
        var id2 = getUUID(); //Gera uma id aleatoria para o parking
        var id3 = getUUID(); //Gera uma id aleatoria para o parking
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking2 = new Parking(id2, "ABC-1234", "SP", "GOLF", "VERMELHO");
        Parking parking3 = new Parking(id3, "SDE-4356", "PE", "SAVEIRO", "BRANCO");
        parkingMap.put(id, parking);
        parkingMap.put(id2, parking2);
        parkingMap.put(id3, parking3);
    }

    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null) {
            throw new ParkingNotFoundException(id);
        } // tratamento de erros
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);

        return parkingCreate;
    }

    public void delete(String id) {
        Parking parking = findById(id);
        parkingMap.remove(id);
    }
}
