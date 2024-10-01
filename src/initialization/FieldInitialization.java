package initialization;

import java.util.Random;

public class FieldInitialization {

    private static int[][] field = new int[10][10];

    // индекс 0 - кол-во кораблей в 1 клетку и т.д
    public static int[] ships = new int[]{4, 3, 2, 1};
    public static final Random RANDOM = new Random();

    public static int[][] getField() {
        return field;
    }

    public static void setField(int[][] field) {
        FieldInitialization.field = field;
    }

    public FieldInitialization() {
        field = new int[10][10];
        ships = new int[]{4, 3, 2, 1};
    }

    // генерация случайного расположения кораблей
    public void randomField() {
        while (ships[0] != 0 || ships[1] != 0 || ships[2] != 0 || ships[3] != 0) {
            int x = -1;
            int y = -1;
            do {
                x = getX();
                y = getY();
            } while (field[x][y] == 1);

            checkForPlaceHorizontal(x, y);

            if (field[x][y] != 1)
                checkForPlaceVertical(x, y);
        }
    }

    // проверка на возможность разместить корабль по горизонтали
    private void checkForPlaceHorizontal(int x, int y) {
        int leftBorder = x;
        int rightBorder = x;
        boolean isStopLeft = false;
        boolean isStopRight = false;

        // границы, в которых потенциально можно разместить корабль
        while (!isStopLeft || !isStopRight) {
            if (rightBorder == 9 || field[rightBorder][y] == 1) {
                if (field[rightBorder][y] == 1)
                    rightBorder -= 2;

                isStopRight = true;
            } else
                rightBorder++;

            if (leftBorder == 0 || field[leftBorder][y] == 1) {
                if (field[leftBorder][y] == 1)
                    leftBorder += 2;

                isStopLeft = true;
            } else
                leftBorder--;
        }

        int length = rightBorder - leftBorder + 1;

        for (int i = 3; i >= 0; i--) {
            if (length >= i + 1 && ships[i] > 0) {
                if (length > i + 1) {
                    // здесь я обрезаю границы, чтобы корректно поставить корабль

                    int space = length - i - 1;
                    int leftCut = RANDOM.nextInt(space);
                    leftBorder += leftCut;

                    space -= leftCut;
                    rightBorder -= space;
                }

                if (placeShipHorizontal(leftBorder, rightBorder, y))
                    ships[i]--;
                break;
            }
        }
    }

    private void checkForPlaceVertical(int x, int y) {
        int upBorder = y;
        int downBorder = y;
        boolean isStopUp = false;
        boolean isStopDown = false;

        // границы, в которых потенциально можно разместить корабль
        while (!isStopUp || !isStopDown) {
            if (downBorder == 9 || field[x][downBorder] == 1) {
                if (field[x][downBorder] == 1)
                    downBorder -= 2;

                isStopDown = true;
            } else
                downBorder++;

            if (upBorder == 0 || field[x][upBorder] == 1) {
                if (field[x][upBorder] == 1)
                    upBorder += 2;

                isStopUp = true;
            } else
                upBorder--;
        }

        int length = downBorder - upBorder;

        for (int i = 3; i >= 0; i--) {
            if (length >= i + 1 && ships[i] > 0) {
                if (length > i + 1) {
                    // здесь я обрезаю границы, чтобы корректно поставить корабль

                    int space = length - i - 1;
                    int leftCut = RANDOM.nextInt(space);
                    upBorder += leftCut;

                    space -= leftCut;
                    downBorder -= space;
                }
                if(placeShipVertical(upBorder, downBorder, x))
                    ships[i]--;

                break;
            }
        }
    }

    private boolean placeShipHorizontal(int fromX, int toX, int y) {
        int[][] newShip = new int[10][10];

        for(int i = 0; i < newShip.length; i++)
            newShip[i] = field[i].clone();

        int prevX = -1;

        for (int i = fromX; i <= toX; i++) {
            // проверяем стоят ли корабли вокруг клетки, в которую мы хотим поставить новый корабль (проверка по сторонам и диагоналям)
            if ((i > 0 && field[i - 1][y] == 1 && prevX != i - 1) || (i < 9 && field[i + 1][y] == 1)
                    || (y > 0 && field[i][y - 1] == 1) || (y < 9 && field[i][y + 1] == 1)
                    || (y > 0 && i > 0 && field[i - 1][y - 1] == 1) || (y < 9 && i > 0 && field[i - 1][y + 1] == 1)
                    || (y > 0 && i < 9 && field[i + 1][y - 1] == 1) || (y < 9 && i < 9 && field[i + 1][y + 1] == 1))
                return false;

            newShip[i][y] = 1;
            prevX = i;
        }

        field = newShip;

        return true;
    }

    private boolean placeShipVertical(int fromY, int toY, int x) {
        int[][] newShip = new int[10][10];

        for(int i = 0; i < newShip.length; i++)
            newShip[i] = field[i].clone();

        int prevY = -1;

        for (int i = fromY; i < toY; i++) {
            // проверяем стоят ли корабли вокруг клетки, в которую мы хотим поставить новый корабль (проверка по сторонам и диагоналям)
            if ((i > 0 && field[x][i - 1] == 1 && prevY != i - 1) || (i < 9 && field[x][i + 1] == 1)
                    || (x > 0 && field[x - 1][i] == 1) || (x < 9 && field[x + 1][i] == 1)
                    || (x > 0 && i > 0 && field[x - 1][i - 1] == 1) || (x < 9 && i > 0 && field[x + 1][i - 1] == 1)
                    || (x > 0 && i < 9 && field[x - 1][i + 1] == 1) || (x < 9 && i < 9 && field[x + 1][i + 1] == 1))
                return false;

            newShip[x][i] = 1;
            prevY = i;
        }

        field = newShip;

        return true;
    }


    public static int getX() {
        return RANDOM.nextInt(10);
    }

    public static int getY() {
        return RANDOM.nextInt(10);
    }
}
