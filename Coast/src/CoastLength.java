import java.io.FileNotFoundException;
import java.util.Scanner;

public class CoastLength 
{
	static int[][] map;
	static int n = 0;
	static int m = 0;
	
	public static void main(String[] args) throws FileNotFoundException
	{	
		String botInput;
		char[] numbers;
		int coast = 0;
	
		Scanner sc = new Scanner(System.in);	//Try to accept Kattis' values new File("coast-sample-1.in")
	    n = sc.nextInt();
	    m = sc.nextInt();
	    n += 2;	//make an additional empty row and column at start and end for easier comparisons
	    m += 2;
	    map = new int[n][m];
	    numbers = new char[m];
		n--;						//reduce both by 1 to save space
	    m--;
	    while (sc.hasNextInt()) 	//Fill the array with the read numbers with a frame of 0 around it
		{
			for(int i = 1; i < n ; i++)
			{
				botInput = sc.next();
				numbers = botInput.toCharArray();
				for(int y = 1; y < m; y++)
				{
					map[i][y] = Integer.parseInt(String.valueOf(numbers[y-1]));
				}
			}
		}
	    //Marks all ocean as a 2 so we can easily find the ocean in the next search
	    lakeFinder(0, 0);

	    		
		//Start searching for coast
		for(int i = 1; i < n; i++)
		{
			for(int y = 1; y < m; y++)
			{
				if(map[i][y] == 1)
				{
					if(map[i - 1][y] >= 2)	//Check up for ocean
					{
						coast++;
						map[i-1][y]++;
					}
					if(map[i + 1][y] >= 2)	//Check down
					{
						coast++;
						map[i+1][y]++;
					}
					if(map[i][y - 1] >= 2)	//Check left
					{
						coast++;
						map[i][y-1]++;
					}
					if(map[i][y + 1] >= 2) 	//Check Right
					{
						coast++;
						map[i][y+1]++;
					}
				}
			}	
		}
		
		/*for(int i = 0; i < n+1; i++)
		{
			for(int y = 0; y < m+1; y++)
			{
				System.out.print(map[i][y]);
			}
			System.out.println("");
		}*/
				
		System.out.println(coast);
		sc.close();
		System.exit(0);
	}
	
	static void lakeFinder(int x, int y)
	{
		map[x][y] = 2;
		
		if(x != n)					//If not on max N we can still look down
			if(map[x + 1][y] == 0)
				lakeFinder(x+1, y);
		if(y != m)					//If not on max M we can still look right
			if(map[x][y + 1] == 0)
				lakeFinder(x, y+1);
		if(x != 0)					//If not on 0 we can still look up
			if(map[x - 1][y] == 0)
				lakeFinder(x-1, y);
		if(y != 0)					//If not on 0 we can still look left
			if(map[x][y - 1] == 0)
				lakeFinder(x, y-1);
	}
}

