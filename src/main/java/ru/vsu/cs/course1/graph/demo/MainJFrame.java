package main.java.ru.vsu.cs.course1.graph.demo;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.java.ru.vsu.cs.course1.graph.AdjMatrixGraph;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;
import ru.vsu.cs.course1.graph.GraphUtils;
import ru.vsu.cs.util.SwingUtils;


public class MainJFrame extends javax.swing.JFrame {
    private AdjMatrixGraph graph = null;
    
    private JPanel paintPanel = null;
    private GraphicsNode svgGraphicsNode = null;
    
    private JFileChooser openJFileChooser = null;
    private JFileChooser txtSaveJFileChooser = null;
    private JFileChooser imgSaveJFileChooser = null;
    
    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        paintAreaJPanel.setLayout(new BorderLayout());
        paintPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics gr) {
                super.paintComponent(gr);

                if (svgGraphicsNode == null) {
                    return;
                }
        
                double scaleX = this.getWidth() / svgGraphicsNode.getPrimitiveBounds().getWidth();
                double scaleY = this.getHeight() / svgGraphicsNode.getPrimitiveBounds().getHeight();
                double scale = Math.min(scaleX, scaleY);
                AffineTransform transform = new AffineTransform(scale, 0, 0, scale, 0, 0);
                svgGraphicsNode.setTransform(transform);
                Graphics2D g2d = (Graphics2D) gr;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                svgGraphicsNode.paint(g2d);
            }
        };
        JScrollPane paintJScrollPane = new JScrollPane(paintPanel);
        paintAreaJPanel.add(paintJScrollPane);
        mainJSplitPane.setDividerLocation(0.5);
        
        openJFileChooser = new JFileChooser();
        txtSaveJFileChooser = new JFileChooser();
        imgSaveJFileChooser = new JFileChooser();
        openJFileChooser.setCurrentDirectory(new File("./input"));
        txtSaveJFileChooser.setCurrentDirectory(new File("./input"));
        imgSaveJFileChooser.setCurrentDirectory(new File("./output"));
        FileFilter txtFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
        FileFilter dotFilter = new FileNameExtensionFilter("DOT files (*.dot)", "dot");
        FileFilter svgFilter = new FileNameExtensionFilter("SVG images (*.svg)", "svg");
        FileFilter pngFilter = new FileNameExtensionFilter("PNG images (*.png)", "png");
        openJFileChooser.addChoosableFileFilter(txtFilter);
        openJFileChooser.addChoosableFileFilter(dotFilter);
        txtSaveJFileChooser.addChoosableFileFilter(txtFilter);
        txtSaveJFileChooser.addChoosableFileFilter(dotFilter);
        imgSaveJFileChooser.addChoosableFileFilter(svgFilter);
        imgSaveJFileChooser.addChoosableFileFilter(pngFilter);

        txtSaveJFileChooser.setAcceptAllFileFilterUsed(false);        
        txtSaveJFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        txtSaveJFileChooser.setApproveButtonText("Save");
        imgSaveJFileChooser.setAcceptAllFileFilterUsed(false);        
        imgSaveJFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        imgSaveJFileChooser.setApproveButtonText("Save");
    }
   
    private static String dotToSvg(String dotSrc) throws IOException {
        MutableGraph g = Parser.read(dotSrc);
        return Graphviz.fromGraph(g).render(Format.PNG).toString();
    }
    
    private void drawSvg(String svg) throws IOException {
        String xmlParser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory df = new SAXSVGDocumentFactory(xmlParser);
        SVGDocument doc = df.createSVGDocument(null, new StringReader(svg));
        UserAgent userAgent = new UserAgentAdapter();
        DocumentLoader loader = new DocumentLoader(userAgent);
        BridgeContext ctx = new BridgeContext(userAgent, loader);
        ctx.setDynamicState(BridgeContext.DYNAMIC);
        GVTBuilder builder = new GVTBuilder();
        svgGraphicsNode = builder.build(ctx, doc);
        
        paintPanel.repaint();
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        mainJSplitPane = new javax.swing.JSplitPane();
        leftJPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inputJTextArea = new javax.swing.JTextArea();
        dfsJButton = new javax.swing.JButton();
        makeGraphButton = new javax.swing.JButton();
        fromJTextField = new javax.swing.JTextField();
        bfsJButton = new javax.swing.JButton();
        resultJLabel = new javax.swing.JLabel();
        jButtonSolution = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaOutputResult = new javax.swing.JTextArea();
        rightJPanel = new javax.swing.JPanel();
        paintAreaJPanel = new javax.swing.JPanel();
        mainJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        openJMenuItem = new javax.swing.JMenuItem();
        txtSaveJMenuItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Работа с Graphviz");

        mainJSplitPane.setBorder(null);

        inputJTextArea.setColumns(20);
        inputJTextArea.setRows(5);
        inputJTextArea.setText("0 5\n4 3\n0 1\n9 12\n6 4\n5 4\n0 2\n11 12\n9 10\n0 6\n7 8\n9 11\n5 3");
        jScrollPane2.setViewportView(inputJTextArea);

        dfsJButton.setText("dfs");
        dfsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dfsJButtonActionPerformed(evt);
            }
        });

        makeGraphButton.setText("Построить граф");
        makeGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeGraphButtonActionPerformed(evt);
            }
        });

        fromJTextField.setText("0");

        bfsJButton.setText("bfs");
        bfsJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bfsJButtonActionPerformed(evt);
            }
        });

        resultJLabel.setText(" ");

        jButtonSolution.setText("Задача 20");
        jButtonSolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSolutionActionPerformed(evt);
            }
        });

        jTextAreaOutputResult.setColumns(20);
        jTextAreaOutputResult.setRows(5);
        jScrollPane1.setViewportView(jTextAreaOutputResult);

        javax.swing.GroupLayout leftJPanelLayout = new javax.swing.GroupLayout(leftJPanel);
        leftJPanel.setLayout(leftJPanelLayout);
        leftJPanelLayout.setHorizontalGroup(
            leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(leftJPanelLayout.createSequentialGroup()
                        .addGroup(leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resultJLabel)
                            .addGroup(leftJPanelLayout.createSequentialGroup()
                                .addComponent(fromJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dfsJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bfsJButton))
                            .addGroup(leftJPanelLayout.createSequentialGroup()
                                .addComponent(jButtonSolution, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(makeGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        leftJPanelLayout.setVerticalGroup(
            leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(makeGraphButton)
                    .addComponent(jButtonSolution))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dfsJButton)
                    .addComponent(bfsJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultJLabel)
                .addContainerGap())
        );

        mainJSplitPane.setLeftComponent(leftJPanel);

        javax.swing.GroupLayout paintAreaJPanelLayout = new javax.swing.GroupLayout(paintAreaJPanel);
        paintAreaJPanel.setLayout(paintAreaJPanelLayout);
        paintAreaJPanelLayout.setHorizontalGroup(
            paintAreaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );
        paintAreaJPanelLayout.setVerticalGroup(
            paintAreaJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rightJPanelLayout = new javax.swing.GroupLayout(rightJPanel);
        rightJPanel.setLayout(rightJPanelLayout);
        rightJPanelLayout.setHorizontalGroup(
            rightJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintAreaJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        rightJPanelLayout.setVerticalGroup(
            rightJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintAreaJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainJSplitPane.setRightComponent(rightJPanel);

        fileJMenu.setText("Файл");

        openJMenuItem.setText("Открыть");
        openJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(openJMenuItem);

        txtSaveJMenuItem.setText("Сохранить описание");
        txtSaveJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaveJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(txtSaveJMenuItem);

        mainJMenuBar.add(fileJMenu);

        setJMenuBar(mainJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainJSplitPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainJSplitPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openJMenuItemActionPerformed
        if (openJFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (Scanner sc = new Scanner(openJFileChooser.getSelectedFile())) {
                sc.useDelimiter("\\Z");                 
                inputJTextArea.setText(sc.next());
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        }
    }//GEN-LAST:event_openJMenuItemActionPerformed

    private void txtSaveJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaveJMenuItemActionPerformed
        if (txtSaveJFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try(FileWriter wr = new FileWriter(txtSaveJFileChooser.getSelectedFile())) {
                wr.write(inputJTextArea.getText());
            } catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
            }
        }        
    }//GEN-LAST:event_txtSaveJMenuItemActionPerformed

    private void dfsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dfsJButtonActionPerformed
        if (graph == null) {
            return;
        }
        try {
            int from = Integer.parseInt(fromJTextField.getText());
            StringBuilder sb = new StringBuilder();
            for (Integer v : graph.dfs(from)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(v);
            }
            resultJLabel.setText(sb.toString());
        } catch (Exception e) {
            SwingUtils.showErrorMessageBox(e);
        }
    }//GEN-LAST:event_dfsJButtonActionPerformed
    
    private void makeGraphButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_makeGraphButtonActionPerformed
        try {
            this.graph = Program.myFromStr(inputJTextArea.getText());
            drawSvg(dotToSvg(graph.toDot()));
        } catch (Exception e) {
            SwingUtils.showErrorMessageBox(e);
        }
    }//GEN-LAST:event_makeGraphButtonActionPerformed

    private void bfsJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bfsJButtonActionPerformed
        if (graph == null) {
            return;
        }
        try {
            int from = Integer.parseInt(fromJTextField.getText());
            StringBuilder sb = new StringBuilder();
            for (Integer v : graph.bfs(from)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(v);
            }
            resultJLabel.setText(sb.toString());
        } catch (Exception e) {
            SwingUtils.showErrorMessageBox(e);
        }
    }//GEN-LAST:event_bfsJButtonActionPerformed

    private void jButtonSolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSolutionActionPerformed
        int result = graph.solution();
        if(result == 2147483647){
            jTextAreaOutputResult.setText("К сожалению, граф не связный. Мы\nимеем дело с инопланетной\nцивилизацией :)");
        }else{
            jTextAreaOutputResult.setText("Данный граф соответствует теории об\n"+result+" рукопожатиях");
        }
    }//GEN-LAST:event_jButtonSolutionActionPerformed
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bfsJButton;
    private javax.swing.JButton dfsJButton;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JTextField fromJTextField;
    private javax.swing.JTextArea inputJTextArea;
    private javax.swing.JButton jButtonSolution;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaOutputResult;
    private javax.swing.JPanel leftJPanel;
    private javax.swing.JMenuBar mainJMenuBar;
    private javax.swing.JSplitPane mainJSplitPane;
    private javax.swing.JButton makeGraphButton;
    private javax.swing.JMenuItem openJMenuItem;
    private javax.swing.JPanel paintAreaJPanel;
    private javax.swing.JLabel resultJLabel;
    private javax.swing.JPanel rightJPanel;
    private javax.swing.JMenuItem txtSaveJMenuItem;
    // End of variables declaration//GEN-END:variables
}
