package panzer.models.miscellaneous;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import panzer.contracts.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleAssemblerTest {
    private static final int MAX_INT = Integer.MAX_VALUE;
    private static final double WEIGHT = 1.0;
    private static final BigDecimal PRICE = BigDecimal.ONE;

    private static final String EXPECTED_AND_ACTUAL_VALUES_MISMATCH = "Expected %1$s and actual %1$s are different!";

    private Assembler vehicleAssembler;
    private List<Part> arsenalParts;
    private List<Part> shellParts;
    private List<Part> enduranceParts;

    @Before
    public void init() {
        this.vehicleAssembler = new VehicleAssembler();

        AttackModifyingPart attackModifyingPart1 = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(attackModifyingPart1.getAttackModifier()).thenReturn(MAX_INT);
        Mockito.when(attackModifyingPart1.getWeight()).thenReturn(WEIGHT);
        Mockito.when(attackModifyingPart1.getPrice()).thenReturn(PRICE);

        AttackModifyingPart attackModifyingPart2 = Mockito.mock(AttackModifyingPart.class);
        Mockito.when(attackModifyingPart2.getWeight()).thenReturn(WEIGHT);
        Mockito.when(attackModifyingPart2.getPrice()).thenReturn(PRICE);
        Mockito.when(attackModifyingPart2.getAttackModifier()).thenReturn(MAX_INT);


        DefenseModifyingPart defenseModifyingPart1 = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(defenseModifyingPart1.getDefenseModifier()).thenReturn(MAX_INT);
        Mockito.when(defenseModifyingPart1.getWeight()).thenReturn(WEIGHT);
        Mockito.when(defenseModifyingPart1.getPrice()).thenReturn(PRICE);

        DefenseModifyingPart defenseModifyingPart2 = Mockito.mock(DefenseModifyingPart.class);
        Mockito.when(defenseModifyingPart2.getDefenseModifier()).thenReturn(MAX_INT);
        Mockito.when(defenseModifyingPart2.getWeight()).thenReturn(WEIGHT);
        Mockito.when(defenseModifyingPart2.getPrice()).thenReturn(PRICE);


        HitPointsModifyingPart hitPointsModifyingPart1 = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(hitPointsModifyingPart1.getHitPointsModifier()).thenReturn(MAX_INT);
        Mockito.when(hitPointsModifyingPart1.getWeight()).thenReturn(WEIGHT);
        Mockito.when(hitPointsModifyingPart1.getPrice()).thenReturn(PRICE);

        HitPointsModifyingPart hitPointsModifyingPart2 = Mockito.mock(HitPointsModifyingPart.class);
        Mockito.when(hitPointsModifyingPart2.getHitPointsModifier()).thenReturn(MAX_INT);
        Mockito.when(hitPointsModifyingPart2.getWeight()).thenReturn(WEIGHT);
        Mockito.when(hitPointsModifyingPart2.getPrice()).thenReturn(PRICE);

        this.vehicleAssembler.addArsenalPart(attackModifyingPart1);
        this.vehicleAssembler.addArsenalPart(attackModifyingPart2);

        this.vehicleAssembler.addEndurancePart(hitPointsModifyingPart1);
        this.vehicleAssembler.addEndurancePart(hitPointsModifyingPart2);

        this.vehicleAssembler.addShellPart(defenseModifyingPart1);
        this.vehicleAssembler.addShellPart(defenseModifyingPart2);

        Field[] fields = this.vehicleAssembler.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        try {
            this.arsenalParts = (ArrayList<Part>)fields[0].get(this.vehicleAssembler);
            this.shellParts = (ArrayList<Part>)fields[1].get(this.vehicleAssembler);
            this.enduranceParts = (ArrayList<Part>)fields[2].get(this.vehicleAssembler);
        } catch (Exception e) {

        }

        for (Field field : fields) {
            field.setAccessible(false);
        }
    }

    @Test
    public void getTotalWeight() {
        double expectedValue = WEIGHT * 6;
        double actualValue = this.vehicleAssembler.getTotalWeight();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUES_MISMATCH, "weight") , expectedValue, actualValue, 0.000001);
    }

    @Test
    public void getTotalPrice() {
        BigDecimal expectedPrice = PRICE.multiply(BigDecimal.valueOf(6));
        BigDecimal actualPrice = this.vehicleAssembler.getTotalPrice();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUES_MISMATCH, "price") , expectedPrice, actualPrice);
    }

    @Test
    public void getTotalAttackModification() {
        long expectedValue = MAX_INT * 2L;
        long actualValue = this.vehicleAssembler.getTotalAttackModification();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUES_MISMATCH, "attack modification") , expectedValue, actualValue);
    }

    @Test
    public void getTotalDefenseModification() {
        long expectedValue = MAX_INT * 2L;
        long actualValue = this.vehicleAssembler.getTotalDefenseModification();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUES_MISMATCH, "defence modification") , expectedValue, actualValue);
    }

    @Test
    public void getTotalHitPointModification() {
        long expectedValue = MAX_INT * 2L;
        long actualValue = this.vehicleAssembler.getTotalHitPointModification();

        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUES_MISMATCH, "hitPoints modification") , expectedValue, actualValue);
    }

    @Test
    public void addArsenalPart() {
        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUES_MISMATCH, "arsenal parts size") , 2, this.arsenalParts.size());
    }

    @Test
    public void addShellPart() {
        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUES_MISMATCH, "shell parts size") , 2, this.shellParts.size());
    }

    @Test
    public void addEndurancePart() {
        Assert.assertEquals(String.format(EXPECTED_AND_ACTUAL_VALUES_MISMATCH, "endurance parts size") , 2, this.enduranceParts.size());
    }
}
