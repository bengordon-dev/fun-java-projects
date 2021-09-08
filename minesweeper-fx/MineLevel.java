import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class MineLevel extends BorderPane
{
    public MineGrid grid;
    public int width;
    public int height;
    public int mines;
    public Pane center;
    public double tileSize;
    public MineTile hoveredOverTile;
    public Text unflaggedMines;

    public MineLevel(int width, int height, int mines, double tileSize)
    {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.tileSize = tileSize;

        this.grid = new MineGrid(width, height, mines, this);

        this.center = new Pane();
        center.setPrefSize(tileSize*width, tileSize*height);
        center.setMinSize(tileSize*width, tileSize*height);
        center.setMaxSize(tileSize*width, tileSize*height);
        setCenter(center);
        setTop(buildTopBar());

        grid.draw();
    }
    public HBox buildTopBar()
    {
        HBox topBar = new HBox(20);
        topBar.setAlignment(Pos.CENTER);

        Button newGame = new Button("New Game");
        
        newGame.setFocusTraversable(false); // stops space from firing the button

        this.unflaggedMines = new Text(Integer.toString(grid.unflaggedMines));

        newGame.setOnAction( e -> {
            center.getChildren().clear();
            grid = new MineGrid(width, height, mines, this);
            grid.draw();
            this.unflaggedMines.setText(Integer.toString(grid.unflaggedMines));
        });

        Button zoomOut = new Button("Zoom Out");
        zoomOut.setFocusTraversable(false);
        zoomOut.setOnAction( e -> {
            this.tileSize *= (9.0/10.0);
            grid.draw();
        });


        Button zoomIn = new Button("Zoom In");
        zoomIn.setFocusTraversable(false);
        zoomIn.setOnAction( e -> {
            this.tileSize *= (10.0/9.0);
            grid.draw();
        });

        topBar.getChildren().addAll(zoomOut, zoomIn, unflaggedMines, newGame);
        return topBar;
    }

}