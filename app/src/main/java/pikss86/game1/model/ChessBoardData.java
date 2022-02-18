package pikss86.game1.model;

import static pikss86.game1.model.ChessBoardData.Status.*;

import org.w3c.dom.*;
import static pikss86.game1.model.ChessBoardData.GameActors.*;

public class ChessBoardData {

    public boolean whiteIsHuman = true;
    public boolean blackIsHuman = true;
    public boolean whiteSessionContinue = true;
    public boolean blackSessionContinue = false;
    public String resultBuf;
    /* The number of own and enemy's checkers */
    public int whiteCheckers = 12;
    public int blackCheckers = 12;
    /* CellSize is a size of chess board cell, it is a square so all sides are same */
    public int CELL_SIZE = 55;
    /* cellSideNumber is using for different kinds of checkers. Russian checkers use chess board 8x8 cells.  */
    public final int CELL_SIDE_NUM = 8;
    public final int CELL_NUM = CELL_SIDE_NUM * CELL_SIDE_NUM;
    public Cell cells[] = new Cell[CELL_NUM];
    /* Each checker's diameter */
    public int checkerX;
    public int checkerY;
    /* The offset from left and top frame bounds  */
    public int OFFSET_LEFT_BOUND = 0;//-50;
    public int OFFSET_TOP_BOUND = 0;//-50;
    /* Diameter of checkers, inner round of queen */
    public int CHECKER_DIAMETER = 50;
    public int QUEEN_INNER_DIAMETER = 40;
    public int QUEEN_INNER_OFFSET = (CHECKER_DIAMETER - QUEEN_INNER_DIAMETER) / 2;
    public Dimension PREFERRED_SIZE = new Dimension(CELL_SIDE_NUM * CELL_SIZE + CELL_SIZE, CELL_SIDE_NUM * CELL_SIZE + CELL_SIZE);
    /* Those arrays we use in  makeIndex() method and in painting numbers of chess board in method paint()*/
    public final String LITERALS[] = {"NULL", "a", "b", "c", "d", "e", "f", "g", "h"};//
    public final int REVERS_NUMBERS[] = {0, 8, 7, 6, 5, 4, 3, 2, 1};
    public String stepWhiteText;
    public String stepBlackText;
    public String userHasFighterText;
    public String userMustFightText;
    public String wrongNextCellText;
    public String frameTitle;
    public String gameTitle;
    public String settingsTitle;
    public String languageTitle;
    public String gameActorsTitle;
    public String compVSuserTitle;
    public String userVScompTitle;
    public String userVSuserTitle;
    public String compVScompTitle;
    public String helpTitle;
    public String newGameTitle;
    public String exitTitle;
    public String rulesTitle;
    public String rulesLink;
    public String aboutTitle;
    public String aboutDeveloperText;
    public String labelBlackTitle;
    public String labelWhiteTitle;
    public String noWhiteCheckersText;
    public String noBlackCheckersText;
    public String whiteIsBlockedText;
    public String blackIsBlockedText;
    public String whiteWon;
    public String blackWon;
    public String dialogNewGame;
    public String dialogExit;
    /* Each cell's coordinates (left upper corner) */
    int cX;
    int cY;
    boolean gameExit = false;

    public boolean isGameOwer() {
        return gameOwer;
    }

    boolean gameOwer = false;
    Lang LANG = Lang.RU;
    private NodeList menuValues;
    private DataListener dataListener;

    public enum Status {

        NILL, WC, BC, WHITE_CH, BLACK_CH, WHITE_Q, BLACK_Q, WHITE_ACH, BLACK_ACH, WHITE_AQ, BLACK_AQ, TBCH;
    }

    enum Lang {

        RU, UA, ENG;
    }

    enum GameActors {

        USERvsCOMP, COMPvsUSER, USERvsUSER, COMPvsCOMP;
    }

    public void setUSERvsCOMP() {
        setGameActors(USERvsCOMP);
    }

    public void setCOMPvsUSER() {
        setGameActors(COMPvsUSER);

    }

    public void setCOMPvsCOMP() {
        setGameActors(COMPvsCOMP);
    }

    public void setUSERvsUSER() {
        setGameActors(USERvsUSER);
    }

    void setGameActors(GameActors GA) {
        if (GA == USERvsCOMP) {
            whiteIsHuman = true;
            blackIsHuman = false;
        }
        if (GA == COMPvsUSER) {
            whiteIsHuman = false;
            blackIsHuman = true;
        }
        if (GA == COMPvsCOMP) {
            whiteIsHuman = false;
            blackIsHuman = false;
        }
        if (GA == USERvsUSER) {
            whiteIsHuman = true;
            blackIsHuman = true;
        }
    }

    public void restartGame() {
        gameOwer = false;
        resetData();
        initCellsArr();
    }

    public void setGameExit() {
        gameExit = true;
    }

    void resetData() {
        gameOwer = true;
        gameOwer = false;
        whiteCheckers = 12;
        blackCheckers = 12;
        whiteSessionContinue = true;
        blackSessionContinue = false;
    }

    public void setEnglishLang() {
        setLanguage(Lang.ENG);
    }

    public void setRussianLang() {
        setLanguage(Lang.RU);

    }

    public void setUkrainianLang() {
        setLanguage(Lang.UA);
    }

    private void setLanguage(Lang lang) {
        LANG = lang;
        stepWhiteText = "--- Белые ходят ---";
        stepBlackText = "--- Черные ходят ---";
        userHasFighterText = "Вы имеете боевую шашку";
        userMustFightText = "Вы должны бить";
        wrongNextCellText = "Выбрана не правильная целевая клетка ";
        frameTitle = "Русские шашки";
        gameTitle = "Игра";
        settingsTitle = "Настройки";
        gameActorsTitle = "Кто играет";
        userVScompTitle = "Компьютер(белыми) VS Игрок(черными)";
        compVSuserTitle = "Игрок(белыми) VS Компьютер(черными)";
        userVSuserTitle = "Игрок(белыми) VS Игрок(черными)";
        compVScompTitle = "Компьютер(белыми) VS Компьютер(черными)";
        languageTitle = "Язык";
        helpTitle = "Помощь";
        newGameTitle = "Новая игра";
        exitTitle = "Выход";
        rulesTitle = "Правила";
        rulesLink = "http://ru.wikipedia.org/wiki/%D0%A0%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B5_%D1%88%D0%B0%D1%88%D0%BA%D0%B8";
        aboutTitle = "О программе";
        aboutDeveloperText = "Разработал Kapellan steel.lights.gardens@gmail.com";
        labelBlackTitle = "Черных шашек: ";
        labelWhiteTitle = "Белых шашек: ";
        noWhiteCheckersText = "Все белые шашки уничтожены - черные победили !";
        noBlackCheckersText = "Все черные шашки уничтожены - белые победили !";
        whiteIsBlockedText = "Все черные шашки заблокировали - белые победили !";
        blackIsBlockedText = "Все белые шашки заблокировали - черные победили !";
        whiteWon = "Белые выиграли";
        blackWon = "Черные выиграли";
        dialogNewGame = "Новая игра";
        dialogExit = "Выход";
        if (dataListener != null) {
            dataListener.updateTextGuiLanguageInfo(new UpdateGuiEvent(this));
        }
    }

    void setCheckersNum() {
        int compNum = 0;
        int userNum = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].isBlack()) {
                compNum++;
            }
            if (cells[i].isWhite()) {
                userNum++;
            }
        }
        blackCheckers = compNum;
        whiteCheckers = userNum;
    }

    /**
     * This method make's alphabet-digital index of any cell
     */
    private String makeIndex(int indexLiteralX, int indexDigitY) {
        return LITERALS[indexLiteralX] + (Integer.toString(REVERS_NUMBERS[indexDigitY]));
    }

    void notifyNoBlackCkeckersLeft() {
        dataListener.noBlackCkeckersLeft(new UpdateGuiEvent(this));
    }

    void notifyNoWhiteCkeckersLeft() {
        dataListener.noWhiteCkeckersLeft(new UpdateGuiEvent(this));
    }

    void notifyBlackIsBlocked() {
        dataListener.blackIsBlocked(new UpdateGuiEvent(this));
    }

    void notifyWhiteIsBlocked() {
        dataListener.whiteIsBlocked(new UpdateGuiEvent(this));
    }

    void notifyUpdateGUI() {
        dataListener.updateGUI(new UpdateGuiEvent(this));
    }

    private void initCellsArr() {
        int cellCount = 0;
        /* The cycle of vertical rows painting */
        for (int vert = 1; vert < CELL_SIDE_NUM + 1; vert++) {
            /* unpaired rows **/
            if (vert % 2 != 0) {
                /* The cycle of horizontal painting. We change black and white squares, also left bottom korner must be grey,
                 *  so we use conditions which check it */
                for (int hor = 1; hor < (CELL_SIDE_NUM + 1); hor++) {
                    cX = OFFSET_LEFT_BOUND + (hor * CELL_SIZE);
                    cY = OFFSET_TOP_BOUND + (vert * CELL_SIZE);
                    /* unpaired cols in unpaired rows are white */
                    if (hor % 2 != 0) {
                        cells[cellCount] = new Cell(makeIndex(hor, vert), cX, cY, WC);
                        cellCount++;
                    }
                    /* paired cols in paired rows are grey **/
                    if (hor % 2 == 0) {
                        /* black cells we arrange by checkers. Cells upper 5 row - white checkers (enemy), under 4 row - black checkers (own) **/
                        if (vert > (CELL_SIDE_NUM / 2 + 1)) {
                            cells[cellCount] = new Cell(makeIndex(hor, vert), cX, cY, WHITE_CH);
                            cellCount++;
                        } else if (vert < (CELL_SIDE_NUM / 2)) {
                            cells[cellCount] = new Cell(makeIndex(hor, vert), cX, cY, BLACK_CH);
                            cellCount++;
                        } else {
                            cells[cellCount] = new Cell(makeIndex(hor, vert), cX, cY, BC);
                            cellCount++;
                        }
                    }
                }
            } else { /* double rows */
                for (int hor = 1; hor < (CELL_SIDE_NUM + 1); hor++) {
                    cX = OFFSET_LEFT_BOUND + (hor * CELL_SIZE);
                    cY = OFFSET_TOP_BOUND + (vert * CELL_SIZE);
                    /* unpaired cols in paired rows are grey */
                    if (hor % 2 != 0) {
                        /* black cells we arrange by checkers. Cells upper 5 row - white checkers (enemy), under 4 row - black checkers (own) **/
                        if (vert > (CELL_SIDE_NUM / 2 + 1)) {
                            cells[cellCount] = new Cell(makeIndex(hor, vert), cX, cY, WHITE_CH);
                            cellCount++;
                        } else if (vert < (CELL_SIDE_NUM / 2)) {
                            cells[cellCount] = new Cell(makeIndex(hor, vert), cX, cY, BLACK_CH);
                            cellCount++;
                        } else {
                            cells[cellCount] = new Cell(makeIndex(hor, vert), cX, cY, BC);
                            cellCount++;
                        }
                    }
                    /* paired cols in paired rows are white */
                    if (hor % 2 == 0) {
                        cells[cellCount] = new Cell(makeIndex(hor, vert), cX, cY, WC);
                        cellCount++;
                    }
                }
            }
        }
    }

    public void addDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    public ChessBoardData() {
        initCellsArr();
    }
}
