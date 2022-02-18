package pikss86.game1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import pikss86.game1.R;
import pikss86.game1.model.Cell;
import pikss86.game1.model.ChessBoardData;
import pikss86.game1.model.Dimension;

class ChessBoard extends View {

    ChessBoardData data;

    Paint mPaint = new Paint();
    ShapeDrawable mDrawable = new ShapeDrawable(new OvalShape());

    int COLOR_WHITE_KLETKA = Color.WHITE;
    int COLOR_BLACK_KLETKA = Color.GRAY;
    int COLOR_WHITE_CHECKER = Color.YELLOW;
    int COLOR_BLACK_CHECKER = Color.BLACK;
    int COLOR_WHITE_DAMKA = Color.CYAN;
    int COLOR_BLACK_DAMKA = Color.GRAY;
    int COLOR_TBCH_CHECKER = Color.rgb(153,255,153);

    //Радиусы разных состояний шашек
    float minus1 = 0.20f;
    float minus2 = 0.50f;
    float minus3 = 0.15f;

    ChessBoard(Context context, ChessBoardData data) {
        super(context);
        this.data = data;

        COLOR_WHITE_KLETKA = context.getResources().getColor(R.color.COLOR_WHITE_KLETKA);
        COLOR_BLACK_KLETKA = context.getResources().getColor(R.color.COLOR_BLACK_KLETKA);
        COLOR_WHITE_CHECKER = context.getResources().getColor(R.color.COLOR_WHITE_CHECKER);
        COLOR_BLACK_CHECKER = context.getResources().getColor(R.color.COLOR_BLACK_CHECKER);
        COLOR_WHITE_DAMKA = context.getResources().getColor(R.color.COLOR_WHITE_DAMKA);
        COLOR_BLACK_DAMKA = context.getResources().getColor(R.color.COLOR_BLACK_DAMKA);
        COLOR_TBCH_CHECKER = context.getResources().getColor(R.color.COLOR_TBCH_CHECKER);

    }

    @Override
    protected void onSizeChanged(final int width, final int height, final int oldw,
                                 final int oldh) {

        data.CELL_SIZE = width/data.CELL_SIDE_NUM;
        data.CHECKER_DIAMETER = data.CELL_SIZE-10;
        data.QUEEN_INNER_DIAMETER = data.CELL_SIZE-15;

        data.OFFSET_LEFT_BOUND = data.OFFSET_LEFT_BOUND-data.CELL_SIZE;
        data.OFFSET_TOP_BOUND = data.OFFSET_TOP_BOUND-data.CELL_SIZE;
        data.QUEEN_INNER_OFFSET = (data.CHECKER_DIAMETER - data.QUEEN_INNER_DIAMETER) / 2;
        data.PREFERRED_SIZE = new Dimension(data.CELL_SIDE_NUM * data.CELL_SIZE + data.CELL_SIZE, data.CELL_SIDE_NUM * data.CELL_SIZE + data.CELL_SIZE);
        data.restartGame();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas g2d) {
        super.onDraw(g2d);

        for (int cCount = 0; cCount < data.CELL_NUM; cCount++) {
            Cell cell = data.cells[cCount];
            data.checkerX = cell.cX + data.CELL_SIZE / 2 - data.CHECKER_DIAMETER / 2;
            data.checkerY = cell.cY + data.CELL_SIZE / 2 - data.CHECKER_DIAMETER / 2;

            switch (cell.getStatus()) {
                case WC:
                    paintCell(COLOR_WHITE_KLETKA, g2d, cell);
                    break;
                case BC:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    break;
                case WHITE_CH:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_WHITE_CHECKER, g2d, cell, minus1);
                    break;
                case BLACK_CH:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_BLACK_CHECKER, g2d, cell, minus1);
                    break;
                case WHITE_ACH:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_BLACK_CHECKER, g2d, cell, minus3);
                    paintOvalShape(COLOR_WHITE_CHECKER, g2d, cell, minus1);
                    break;
                case BLACK_ACH:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_WHITE_CHECKER, g2d, cell, minus3);
                    paintOvalShape(COLOR_BLACK_CHECKER, g2d, cell, minus1);
                    break;
                case WHITE_Q:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_WHITE_CHECKER, g2d, cell, minus1);
                    paintOvalShape(COLOR_WHITE_DAMKA, g2d, cell, minus2);
                    break;
                case BLACK_Q:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_BLACK_CHECKER, g2d, cell, minus1);
                    paintOvalShape(COLOR_BLACK_DAMKA, g2d, cell, minus2);
                    break;
                case WHITE_AQ:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_BLACK_CHECKER, g2d, cell, minus3);
                    paintOvalShape(COLOR_WHITE_CHECKER, g2d, cell, minus1);
                    paintOvalShape(COLOR_WHITE_DAMKA, g2d, cell, minus2);
                    break;
                case BLACK_AQ:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_WHITE_CHECKER, g2d, cell, minus3);
                    paintOvalShape(COLOR_BLACK_CHECKER, g2d, cell, minus1);
                    paintOvalShape(COLOR_BLACK_DAMKA, g2d, cell, minus2);
                    break;
                case TBCH:
                    paintCell(COLOR_BLACK_KLETKA, g2d, cell);
                    paintOvalShape(COLOR_TBCH_CHECKER, g2d, cell, minus1);
                    break;
            } // end of switch

        }// end of for
    }

    private void paintCell(int color, Canvas g2d, Cell cell) {
        mPaint.setColor(color);
        g2d.drawRect(cell.cX, cell.cY, cell.cX + data.CELL_SIZE, cell.cY + data.CELL_SIZE, mPaint);
    }

    private void paintOvalShape(int color, Canvas g2d, Cell cell, float w) {
        int d = (int)(data.CELL_SIZE*w/2);

        mDrawable.getPaint().setColor(color);
        mDrawable.setBounds(new Rect(cell.cX+d, cell.cY+d, cell.cX+data.CELL_SIZE-d, cell.cY+data.CELL_SIZE-d));
        mDrawable.draw(g2d);
    }

}
