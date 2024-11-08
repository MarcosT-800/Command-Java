// commands/CutCommand.java
package commands;

import editor.Editor;

public class CutCommand extends Command {

    public CutCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        if (editor.getSelectedText().isEmpty()) return false;

        backup();
        editor.setClipboard(editor.getSelectedText());
        editor.setText(editor.cutText());
        return true;
    }
}
