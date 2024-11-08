// commands/PasteCommand.java
package commands;

import editor.Editor;

public class PasteCommand extends Command {

    public PasteCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        if (editor.getClipboard() == null || editor.getClipboard().isEmpty()) {
            return false;
        }

        backup();
        editor.insertTextAtCursor(editor.getClipboard());
        return true;
    }
}
