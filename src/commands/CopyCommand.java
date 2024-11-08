// commands/CopyCommand.java
package commands;

import editor.Editor;

public class CopyCommand extends Command {

    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.setClipboard(editor.getSelectedText());
        return true;
    }
}
