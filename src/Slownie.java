//Bartosz Sulkowski 14.12.2000
//zamiana liczb w zapisie dziesietnym na slowny
//tlumaczenie na Jave Aleksander Sulkowski 30.12.2001.
class Slownie
{
	private final String[][] slo =
	{{"jeden ","jedena\u015Bcie ","dziesi\u0119\u0107 ","sto "}
	,{"dwa ","dwana\u015Bcie ","dwadzie\u015Bcia ","dwie\u015Bcie "}
	,{"trzy ","trzyna\u015Bcie ","trzydzie\u015Bci ","trzysta "}
	,{"cztery ","czterna\u015Bcie ","czterdzie\u015Bci ","czterysta "}
	,{"pi\u0119\u0107 ","pi\u0119tna\u015Bcie ","pi\u0119\u0107dziesi\u0105t ","pi\u0119\u0107set "}
	,{"sze\u015B\u0107 ","szesna\u015Bcie ","sze\u015B\u0107dziesi\u0105t ","sze\u015B\u0107set "}
	,{"siedem ","siedemna\u015Bcie ","siedemdziesi\u0105t ","siedemset "}
	,{"osiem ","osiemna\u015Bcie ","osiemdziesi\u0105t ","osiemset "}
	,{"dziewi\u0119\u0107 ","dziewi\u0119tna\u015Bcie ","dziewi\u0119\u0107dziesi\u0105t ","dziewi\u0119\u0107set "}};
	private final String[][] zer =
	{{"jeden ","",""}
	,{"tysi\u0105c ","tysi\u0105ce ","tysi\u0119cy "}
	,{"milion ","miliony ","milion\u00F3w "}
	,{"miliard ","miliardy ","miliard\u00F3w "}};
	
	private int value;
	private String string = new String();
	
	public Slownie(int l)
	{
		value = l;
		makeString();
	}

	public String getString()
	{
		return string;
	}

	private void makeString()
	{
		if (value == 0)
		{
		    string = new String("zero ");
		    return;
		}
		int length = 0,t,a,b,c;
		int[] th = new int[4];
		int tmp = value;
		for (t = 0; t <= 3; t++)
		{
			th[t] = tmp % 1000;
			tmp /= 1000;
		}
	
		for (t=3; t>=0; t--)
		{
			if (th[t] == 0)
			continue;
			if (th[t] == 1)
			{
				string += zer[t][0];
				continue;
			}
			a=(th[t]/100)%10;
			b=(th[t]/10)%10;
			c=(th[t]/1)%10;
			if (a != 0)
			{
				string += slo[a-1][3];
			}
			if (b == 1 && c != 0)
			{
				string += slo[c-1][1];
			}
			else
			{
				if (b != 0)
				{
					string += slo[b-1][2];
				}
				if (c != 0)
				{
					string += slo[c-1][0];
				}
			}
			if ( 1 < c && c < 5)
			{
				string += zer[t][1];
			}
			else
			{
				string += zer[t][2];
			}
		}
	}
};



