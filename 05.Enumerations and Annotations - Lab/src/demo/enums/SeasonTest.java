package demo.enums;

public class SeasonTest {
    public static void main(String[] args) {
        Season[] seasons = Season.values();
        //.name() and .toString() do the same for now, but toString() can be overridden and .name can't

        for (Season season : seasons) {
            makeSound(season);
        }

        Season spring = Season.valueOf(Season.class, "SPRING"); // don't know what's the difference between this and the other one
        spring = Season.valueOf("SPRING");
        System.out.println(spring);
    }

    private static void makeSound(Season season) {
        StringBuilder sb = new StringBuilder();

        switch (season) {
            case SUMMER:
                sb.append("Ice cream!!!");
                break;

            case SPRING:
                sb.append("Alergies!");
                break;

            case FALL:
                sb.append("School!");
                break;

            case WINTER:
                sb.append("Snow!");
                break;
        }

        sb.append(" --> ordinal of season = ").append(season.ordinal())
                .append(", and the .name method returns: ").append(season.name())
                .append(" | The overridden .toString() method now returns: ").append(season.toString());

        System.out.println(sb.toString());
    }

}
