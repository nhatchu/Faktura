import javax.swing.table.*;
public class ColumnModel extends DefaultTableColumnModel
{
	public ColumnModel()
	{
		TableColumn tc1 = new TableColumn(0, 15);
		tc1.setHeaderValue(DataModel.ColumnsTitles[0]);
		addColumn(tc1);
		
		TableColumn tc2 = new TableColumn(1, 190);
		tc2.setHeaderValue(DataModel.ColumnsTitles[1]);
		addColumn(tc2);
		
		TableColumn tc3 = new TableColumn(2, 40);
		tc3.setHeaderValue(DataModel.ColumnsTitles[2]);
		addColumn(tc3);
		
		TableColumn tc4 = new TableColumn(3, 30);
		tc4.setHeaderValue(DataModel.ColumnsTitles[3]);
		addColumn(tc4);
		
		TableColumn tc5 = new TableColumn(4);
		tc5.setHeaderValue(DataModel.ColumnsTitles[4]);
		addColumn(tc5);
		
		TableColumn tc6 = new TableColumn(5);
		tc6.setHeaderValue(DataModel.ColumnsTitles[5]);
		addColumn(tc6);
		
		TableColumn tc7 = new TableColumn(6, 40);
		tc7.setHeaderValue(DataModel.ColumnsTitles[6]);
		addColumn(tc7);
		
		TableColumn tc8 = new TableColumn(7, 30);
		tc8.setHeaderValue(DataModel.ColumnsTitles[7]);
		addColumn(tc8);
		
		TableColumn tc9 = new TableColumn(8);
		tc9.setHeaderValue(DataModel.ColumnsTitles[8]);
		addColumn(tc9);
		
		TableColumn tc10 = new TableColumn(9);
		tc10.setHeaderValue(DataModel.ColumnsTitles[9]);
		addColumn(tc10);
	}
}
