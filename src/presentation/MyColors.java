package presentation;

import java.awt.*;

public enum MyColors {
    _FFDB6F(new Color(255, 219, 111)),
    _3CFFFF(new Color(60, 255, 255)),
    _00FFFF(new Color(0, 255, 255)),
    _0000FF(new Color(0, 0, 255)),
    _0044AD(new Color(0,68, 173)),
    _0074B3(new Color(0, 116, 173)),
    _505050(new Color(80, 80, 80)),
    _808080(new Color(128, 128, 128)),
    _00FFA2(new Color(0, 255, 162)),
    _006E00(new Color(0, 110, 0)),
    _AB00C8(new Color(171, 0, 200)),
    _D20000(new Color(210, 0, 0)),
    _FF4141(new Color(255, 65, 65)),
    _FFD700(new Color(200,215,0));

    private final Color color;
    MyColors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
