package pikss86.game1.controller;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import pikss86.game1.model.Cell;
import pikss86.game1.model.ChessBoardData;
import pikss86.game1.model.DataListener;
import pikss86.game1.model.Logic;
import pikss86.game1.model.UpdateGuiEvent;
import pikss86.game1.view.MyActivity;

public class ActionChessBoard implements View.OnTouchListener, DataListener {

    private Logic logic;
    private ChessBoardData data;
    private MyActivity mainW;

    public ActionChessBoard(ChessBoardData data, Logic logic, MyActivity mainW) {
        this.data = data;
        this.logic = logic;
        this.mainW = mainW;
    }

    public void noBlackCkeckersLeft(UpdateGuiEvent e) {
        mainW.noBlackCkeckersLeft();
    }

    public void noWhiteCkeckersLeft(UpdateGuiEvent e) {
        mainW.noWhiteCkeckersLeft();
    }

    public void blackIsBlocked(UpdateGuiEvent e) {
        mainW.blackIsBlocked();
    }

    public void whiteIsBlocked(UpdateGuiEvent e) {
        mainW.whiteIsBlocked();
    }

    public void updateGUI(UpdateGuiEvent e) {
        mainW.updateGui();
    }

    public void updateTextGuiLanguageInfo(UpdateGuiEvent e) {
        mainW.updateTextGuiLanguageInfo();
    }

    private Cell getActiveChecker() {
        for (Cell cell : data.cells) {
            if (data.whiteIsHuman) {
                if (cell.isWhiteActiveChecker() || cell.isWhiteActiveQueen()) {
                    return cell;
                }
            }
            if (data.blackIsHuman) {
                if (cell.isBlackActiveChecker() || cell.isBlackActiveQueen()) {
                    return cell;
                }
            }
        }
        return new Cell();
    }

    private boolean existActiveChecker() {
        if (getActiveChecker().isExist()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {

        if ((!data.whiteIsHuman) && (!data.blackIsHuman)) {
            Log.d("T", "touch 1");
            return false;
        }

            Cell clickedCell = logic.getCellByXY((int)e.getX(), (int)e.getY());

        if (data.isGameOwer()) {
            data.restartGame();
            updateGUI(new UpdateGuiEvent(new Object()));
        }


            if (logic.data.whiteSessionContinue) {
                if (data.whiteIsHuman) {
                    if (!(clickedCell.isWhite() || clickedCell.isBlackCell())) {
                        Log.d("T", "touch 2");
                        return false;
                    }
                } else {
                    Log.d("T", "touch 3");
                    return false;
                }
            }
            if (logic.data.blackSessionContinue) {
                if (data.blackIsHuman) {
                    if (!(clickedCell.isBlack() || clickedCell.isBlackCell())) {
                        Log.d("T", "touch 4");
                        return false;
                    }
                } else {
                    Log.d("T", "touch 5");
                    return false;
                }
            }


            if (!existActiveChecker()) {
                clickedCell.setActive();
                updateGUI(new UpdateGuiEvent(new Object()));
                Log.d("T", "touch 6");
                return false;
            }


            if (existActiveChecker()) {
                if (getActiveChecker().equals(clickedCell)) {
                    clickedCell.resetActive();
                    updateGUI(new UpdateGuiEvent(new Object()));
                    Log.d("T", "touch 7");
                    return false;
                }
            }


            if (existActiveChecker() && (clickedCell.isChecker() || clickedCell.isQueen())) {
                getActiveChecker().resetActive();
                clickedCell.setActive();
                updateGUI(new UpdateGuiEvent(new Object()));
                Log.d("T", "touch 8");
                return false;
            }


            if (existActiveChecker()) {
                Cell activeCell = getActiveChecker();
                Cell targetCell = clickedCell;
                logic.userStep(activeCell, targetCell);
                updateGUI(new UpdateGuiEvent(new Object()));
                Log.d("T", "touch 9");
                return false;
            }
        Log.d("T", "touch 10");



        return false;
    }
}
