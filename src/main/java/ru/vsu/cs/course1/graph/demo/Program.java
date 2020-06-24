package main.java.ru.vsu.cs.course1.graph.demo;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import static guru.nidi.graphviz.attribute.Label.Justification.LEFT;
import guru.nidi.graphviz.attribute.LinkAttr;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.attribute.Records;
import static guru.nidi.graphviz.attribute.Records.rec;
import static guru.nidi.graphviz.attribute.Records.turn;
import static guru.nidi.graphviz.model.Compass.*;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import static guru.nidi.graphviz.model.Factory.between;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;
import static guru.nidi.graphviz.model.Factory.port;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.Node;
import guru.nidi.graphviz.parse.Parser;
import java.awt.EventQueue;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.UIManager;

import main.java.ru.vsu.cs.course1.graph.AdjMatrixGraph;
import ru.vsu.cs.util.SwingUtils;

public class Program {

    public static void test1() throws IOException {
        Graph g = graph("example1").directed()
            .graphAttr().with(RankDir.LEFT_TO_RIGHT)
            .with(
                node("a").with(Color.RED).link(node("b")),
                node("b").link(to(node("c")).with(Style.DASHED))
            );
        Graphviz.fromGraph(g).height(100).render(Format.PNG).toFile(new File("_test/ex1.png"));
        System.out.println(Graphviz.fromGraph(g).render(Format.SVG).toString());
    }

    public static void test2() throws IOException {
        Node main = node("main").with(Label.html("<b>main</b><br/>start"), Color.rgb("1020d0").font()),
            init = node(Label.markdown("**_init_**")),
            execute = node("execute"),
            compare = node("compare").with(Shape.RECTANGLE, Style.FILLED, Color.hsv(.7, .3, 1.0)),
            mkString = node("mkString").with(Label.lines(LEFT, "make", "a", "multi-line")),
            printf = node("printf");

        Graph g = graph("example2").directed().with(
            main.link(
                to(node("parse").link(execute)).with(LinkAttr.weight(8)),
                to(init).with(Style.DOTTED),
                node("cleanup"),
                to(printf).with(Style.BOLD, Label.of("100 times"), Color.RED)),
            execute.link(
                graph().with(mkString, printf),
                to(compare).with(Color.RED)),
            init.link(mkString)
        );

        Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File("_test/ex2.png"));
    }

    public static void test3() throws IOException {
        Node node0 = node("node0").with(Records.of(rec("f0", ""), rec("f1", ""), rec("f2", ""), rec("f3", ""), rec("f4", ""))),
            node1 = node("node1").with(Records.of(turn(rec("n4"), rec("v", "719"), rec("")))),
            node2 = node("node2").with(Records.of(turn(rec("a1"), rec("805"), rec("p", "")))),
            node3 = node("node3").with(Records.of(turn(rec("i9"), rec("718"), rec("")))),
            node4 = node("node4").with(Records.of(turn(rec("e5"), rec("989"), rec("p", "")))),
            node5 = node("node5").with(Records.of(turn(rec("t2"), rec("v", "959"), rec("")))),
            node6 = node("node6").with(Records.of(turn(rec("o1"), rec("794"), rec("")))),
            node7 = node("node7").with(Records.of(turn(rec("s7"), rec("659"), rec(""))));
        Graph g = graph("example3").directed()
            .graphAttr().with(RankDir.LEFT_TO_RIGHT)
            .with(
                node0.link(
                    between(port("f0"), node1.port("v", SOUTH)),
                    between(port("f1"), node2.port(WEST)),
                    between(port("f2"), node3.port(WEST)),
                    between(port("f3"), node4.port(WEST)),
                    between(port("f4"), node5.port("v", NORTH))
                ),
                node2.link(between(port("p"), node6.port(NORTH_WEST))),
                node4.link(between(port("p"), node7.port(SOUTH_WEST)))
            );
        Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File("_test/ex3.png"));
    }

    public static void test4() throws IOException {
        MutableGraph g = Parser.read(new File("_test/color.dot"));
        Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("_test/ex4-1.png"));

        g.graphAttrs()
                .add(Color.WHITE.gradient(Color.rgb("888888")).background().angle(90))
                .nodeAttrs().add(Color.WHITE.fill())
                .nodes().forEach(node ->
                node.add(
                        Color.named(node.name().toString()),
                        Style.lineWidth(4).and(Style.FILLED)));
        Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("_test/ex4-2.png"));


    }

    /**
     * Основная функция программы
     *
     * @param args Параметры командной строки
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        /*
        test1();
        test2();
        test3();
        test4();

        if (true) {
            return;
        }
        */
        Locale.setDefault(Locale.ROOT);
        //SwingUtils.setLookAndFeelByName("Windows");
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 20);

        EventQueue.invokeLater(() -> {
            try {
                JFrame mainFrame = new MainJFrame();
                mainFrame.setVisible(true);
                mainFrame.setExtendedState(MAXIMIZED_BOTH);
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });
    }
    public static AdjMatrixGraph myFromStr(String str) throws IOException, InstantiationException, IllegalAccessException {
        AdjMatrixGraph graph = new AdjMatrixGraph();
        if (Pattern.compile("^\\s*\\d+").matcher(str).find()) {
            Scanner scanner = new Scanner(str);
            while(scanner.hasNextLine()) {
                graph.addAdge(scanner.nextInt(), scanner.nextInt());
            }
        }
        return graph;
    }
}
