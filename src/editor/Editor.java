// editor/Editor.java
package editor;

import commands.CopyCommand;
import commands.CutCommand;
import commands.PasteCommand;
import commands.Command;
import commands.CommandHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor {
    private JTextArea textArea;
    private String clipboard;
    private CommandHistory history;

    public Editor() {
        history = new CommandHistory();
    }

    public void init() {
        JFrame frame = new JFrame("Editor de Texto");
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        panel.add(new JScrollPane(textArea));

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButtons(buttonsPanel);

        panel.add(buttonsPanel);

        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addButtons(JPanel buttonsPanel) {
        JButton copyButton = new JButton("Copiar");
        JButton cutButton = new JButton("Cortar");
        JButton pasteButton = new JButton("Colar");
        JButton undoButton = new JButton("Desfazer");

        copyButton.addActionListener(e -> executeCommand(new CopyCommand(this)));
        cutButton.addActionListener(e -> executeCommand(new CutCommand(this)));
        pasteButton.addActionListener(e -> executeCommand(new PasteCommand(this)));
        undoButton.addActionListener(e -> undo());

        buttonsPanel.add(copyButton);
        buttonsPanel.add(cutButton);
        buttonsPanel.add(pasteButton);
        buttonsPanel.add(undoButton);
    }

    public String getText() {
        return textArea.getText();
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getSelectedText() {
        return textArea.getSelectedText();
    }

    public void setClipboard(String text) {
        clipboard = text;
    }

    public String getClipboard() {
        return clipboard;
    }

    public String cutText() {
        String selectedText = getSelectedText();
        setText(getText().replace(selectedText, ""));
        return selectedText;
    }

    public void insertTextAtCursor(String text) {
        int cursorPosition = textArea.getCaretPosition();
        textArea.insert(text, cursorPosition);
    }

    private void executeCommand(Command command) {
        if (command.execute()) {
            history.push(command);
        }
    }

    private void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            if (command != null) {
                command.undo();
            }
        }
    }
}
