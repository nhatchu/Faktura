import javax.swing.event.*;
import javax.swing.JButton;
import javax.swing.JTable;

class ListSelListener implements ListSelectionListener
{
    public ListSelListener(JButton bt1, JButton bt2, JTable tb)
    {
        _bt1 = bt1;
        _bt2 = bt2;
        _tb = tb;
    }
    public void valueChanged(ListSelectionEvent e)
    {
        if (_tb.getSelectedRow() != -1)
        {
            _bt1.setEnabled(true);
            _bt2.setEnabled(true);
        }
        else
        {
            _bt1.setEnabled(false);
            _bt2.setEnabled(false);
        }
    }
    private JButton _bt1;
    private JButton _bt2;
    private JTable _tb;
}
