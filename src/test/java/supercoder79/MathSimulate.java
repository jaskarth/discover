package supercoder79;

public final class MathSimulate {
    public static void main(String[] args) {
        decrementRadius();
    }

    private static void decrementRadius() {
        int depth = 128;

        double radius = 8;

        for (int y = 128; y >= 0; y--) {
            System.out.println(y - 64 + " " + radius + " " + countRad(radius));

            radius -= (1 / 24.0);
        }
    }

    private static int countRad(double rad) {
        int c = 0;
        for (int x = -8; x <= 8; x++) {
            for (int z = -8; z <= 8; z++) {
                double dx = x / rad;
                double dz = z / rad;

                if (dx * dx + dz * dz < 1) {
                    c++;
                }
            }
        }

        return c;
    }
}
