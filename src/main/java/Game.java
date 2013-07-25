import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;


public class Game {
	
	//콘솔 입력 
	static Scanner scoreInput = new Scanner(System.in);

	//점수판 정보 
	static int scores[] = new int[10];
	
	//전체 점수 보관용 
	static int allframes[][] = new int[2][12];
		
	
	//시작 메시지 
	public static void start() {
		System.out.println("\n\t  볼링 점수 계산기 ");		
	}

	// 초기화 
	public static void init() {
		try {
			for(int l1 = 0; l1 < 10; l1++) scores[l1] = 0;
			for(int l2 = 0; l2 < 12; l2++) allframes[0][l2] = allframes[1][l2] = 0;							
		}
		catch (IndexOutOfBoundsException ex)
		{
			System.out.println("초기값 지정 오류가 발생하였습니다. ");
		}
	}
	
	// 프레임 시작 
	public static void nextframe() {
		
		for(int i = 0; i < 10; i++) 
		{
			boolean chk = false;
			int Pins = 0;

			while(!chk) {
			    try
			    {				    	
					System.out.printf("\n\t 현재 프레임 %2d", (i + 1));
				    System.out.printf("\n\t 투구1 점수를 입력하세요: ");

			    	Pins = scoreInput.nextInt();
			    
			 		if(Pins <= 10 && Pins >= 0) {
			 			allframes[0][i] = Pins;
			 			chk = true;
			 			sumResult();
			 			printFrameResult(i+1, 1);
			 		}
			 		else {
			 			printError();
			 		}
			    }
			    catch (InputMismatchException ex) {
			 		//입력값 오류 
			 		if(!chk) printError();
			 		scoreInput.nextLine();
			    }
			}

			    
			if(Pins == 10) {
				printStrike();
				continue;
			}
			    
			chk = false;
			    
			do 
			{
				int Pins2 = 0;

				if(chk) break;
				
				try {
					System.out.printf("\n\t 현재 프레임 %2d", (i + 1));   
					System.out.print("\n\t 투구2 점수를 입력하세요: ");
					Pins2 = scoreInput.nextInt();

					// 두번째 값을 검증한다.
					if(Pins2 <= 10 && Pins2 >= 0 && Pins2 + allframes[0][i] < 11) 
					{
					   allframes[1][i] = Pins2;

					   if(Pins2 + allframes[0][i] == 10) printSpare();
					   chk = true;
					   sumResult();
					   printFrameResult(i+1, 2);				   
					}
			 		else {
			 			printError();
			 		}
				}				
			    catch (InputMismatchException ex) {
			    	//입력값 오류 
			    	if(!chk) printError();
			    	scoreInput.nextLine();
			    }

			} while(true);

		}		
	}
	
	
	//보너스 프레임 입력 
	private static void bonusframe() {
		// TODO Auto-generated method stub
		
		//10 프레임에서 스트라이크 		
		if(allframes[0][9] == 10) 
		{
			boolean chk_error1 = false;
			int bonus1 = 0;

		    while(!chk_error1) 
		    {
		    	try{
			    	System.out.print("\n\t 현재 프레임 보너스1\n\t 투구1 점수를 입력하세요 : ");
			    	bonus1 = scoreInput.nextInt();
	
			    	if(bonus1 < 11 && bonus1 >= 0) {
			    		allframes[0][10] = bonus1;
			    		chk_error1 = true;
			    		
			 			sumResult();
			 			printFrameResult(11, 1);
			    	}
		    	}
			    catch (InputMismatchException ex) {
			    	if(!chk_error1) printError();
			    	scoreInput.nextLine();
			    }
		    }
			
		    // 보너스 프레임에서 스트라이크 
		    if(allframes[0][10] == 10) 
		    {
		    	boolean chk_error2 = false;
		    	int bonus2 = 0;

		    	printStrike();

		    	if(allframes[0][11] == 10) printStrike();

		    	 while(!chk_error2) {

		    		try{
						System.out.print("\n\t 현재 프레임 보너스2\n\t 투구2 점수를 입력하세요: ");
						bonus2 = scoreInput.nextInt();
						
						if(bonus2 < 11 && bonus2 > 0) {
							allframes[0][11] = bonus2;
							chk_error2 = true;			
						}
						else {
							if (!chk_error2) printError();
						}
		    		}
				    catch (InputMismatchException ex) {
			    		//오류인 경우 
			    		if (!chk_error2) printError();
			    		scoreInput.nextLine();
				    }	
		    	}
		    } else {
		    	boolean chk_error3 = false;

		    	int bonus3 = 0;

		    	while(!chk_error3) {
		    		
		    		try {
			    		System.out.print("\n\t 현재 프레임 보너스2\n\t 투구2 점수를 입력하세요: ");
			    		bonus3 = scoreInput.nextInt();
	
			    		if(bonus3 < 11 && bonus3 >= 0 && bonus3 + allframes[0][10] < 11)
			    		{
			    		   allframes[1][10] = bonus3;
			    		   chk_error3 = true;
			    		}
			    		else {	
				    		//오류인 경우 
				    		if (!chk_error3) printError();
			    		}
		    		}
		    		catch (InputMismatchException ex) {
			    		//오류인 경우 
			    		if (!chk_error3) printError();
			    		scoreInput.nextLine();
		    		}
		    	}
		    }

		}
		else if(allframes[0][9] + allframes[1][9] == 10)
		{
			//10 프레임에서 스페어 처리  			
			boolean chk_error4 = false;

			int bonus4 = 0;

			while(!chk_error4){
				try {
					System.out.print("\n\t 현재 프레임 보너스2\n\t 투구2 점수를 입력하세요: ");
					bonus4 = scoreInput.nextInt();
	
					if(bonus4 <= 10 && bonus4 >= 0){
						allframes[0][10] = bonus4;
						chk_error4 = true;
			    	}
					else {
			    		//오류인 경우 
			    		if (!chk_error4) printError();
					}
				}
	    		catch (InputMismatchException ex) {
		    		//오류인 경우 
		    		if (!chk_error4) printError();
		    		scoreInput.nextLine();
	    		}						
			}
		}		
	}
	
	// 결과를 집계한다. 
	public static void sumResult() {

		//첫프레임 점수를 먼저 계산한다.
		//스패어 처리시 
		if(allframes[0][0] + allframes[1][0] == 10)
			scores[0] = 10 + allframes[0][1];
		else
			scores[0] = allframes[0][0] + allframes[1][0];
		
		//스트라이크 처리시 
		if(allframes[0][0] == 10) {
			if(allframes[0][1] == 10)
				scores[0] = 20 + allframes[0][2];
			else
				scores[0] = 10 + allframes[0][1] + allframes[1][1];
		}

		//2번째부터 10번째의 프레임 계산 	
		for(int j = 1; j < 10; j++) 
		{
			//스트라이크 처리시.
			if(allframes[0][j] == 10)
			{
				//연속 스트라이크 처리시.	
				if(allframes[0][j + 1] == 10) scores[j] = scores[j - 1] + 20 + allframes[0][j + 2];
				else scores[j] = scores[j - 1] + 10 + allframes[0][j + 1] + allframes[1][j + 1];
				continue;
			 }
		
			//스패어 처리시. 
			if(allframes[0][j] + allframes[1][j] == 10) {
				scores[j] = scores[j - 1] + 10 + allframes[0][j + 1];
			} else {
				scores[j] = scores[j - 1] + allframes[0][j] + allframes[1][j];
			}  
		}
	}
	
	
	//오류 알림
	public static void printError() {
		System.out.println("\n\t 입력값이 잘못되었습니다.\n\t 0 ~ 10 사이의 값을 입력하세요.");
	}
	
	//스트라이크 인쇄
	public static void printStrike() {
		System.out.println("\n\t\t\t 스트라이크 !!");
	}

	//스페어 인쇄
	public static void printSpare() {
		System.out.println("\n\t\t\t 스패어 !!");
	}

	// 진행중인 결과 인쇄  
	public static void printFrameResult(int curframe, int boll) {
  	
		int vframe = curframe;
		
		//1차 투구인 경우 
		if (boll == 1) {
			vframe = curframe - 1;
		}
	
		printStartPart();
		System.out.print("\t프레임");
		
		for(int kcount = 1; kcount <= curframe; kcount++)
		{	
			if (kcount > 10 ) break;
			System.out.printf("%4d", kcount);
		}
		
		if (curframe == 11)
		{
			//10 프레임이 10점인 경우 
			if(allframes[0][9] == 10) 
			{
				if(allframes[0][10] == 10) System.out.print("  B1 B2");
				else System.out.print("  B");
			}
			else if(allframes[0][9] + allframes[1][9] == 10)
			{	
				System.out.print("  B");
			}
		}
		
		printStartline();
		System.out.print("\t투구1 ");
		
		for(int l = 0; l < curframe; l++)
		{
			if (l >= 10 ) break;
			System.out.printf("%4d", allframes[0][l]);
		}

		if (curframe == 11)
		{
			if(allframes[0][9] == 10)	
			{
				if(allframes[0][10] == 10) System.out.printf("%4d%4d", allframes[0][10], allframes[0][11]);
				else System.out.printf("%4d", allframes[0][10]);	
			} else {
				if(allframes[0][9] + allframes[1][9] == 10) System.out.printf("%4d", allframes[0][10]);
			}
		}
		
		printStartline();   	  	
		System.out.print("\t투구2 ");
		
		
		for(int i1 = 0; i1 < vframe; i1++) {
			if (i1 > 10 ) break;
			System.out.printf("%4d", allframes[1][i1]);
		}
		
		if(allframes[0][9] == 10 && allframes[0][10] != 10)
			System.out.printf("%4d", allframes[1][10]);
		
		printStartline();
		System.out.print("\t점수 ");
		
		for(int j1 = 0; j1 < vframe; j1++) {
			if (j1 > 10 ) break;
			System.out.printf("%4d", scores[j1]);
		}
	
		printStartPart();
	}	
	
	public static void printStartline() {
		System.out.print("\n\t------------------------------------------------------------\n");
	}

	public static void printStartPart() {
		System.out.print("\n\t=============================================================\n");
	}
	
	//스트라이크 마크, 스페이마크 
	public static String printStrikeMark(Integer score) {
		if (score == 10) return "X";
		return  String.valueOf(score);
	}

	
	// 결과 인쇄  
	public static void printResult() {
		//Print
		printStartPart();
		System.out.print("\t\t\t전체 결과표 ");
		printStartPart();		
		
		System.out.print("\t프레임");
		for(int kcount = 1; kcount < 11; kcount++)
		{
			System.out.print(" | ");	
			System.out.printf("%2d", kcount);
		}
		
		if(allframes[0][9] == 10) 
		{
			if(allframes[0][10] == 10) System.out.print(" | B1 | B2");
			else System.out.print(" | B");
		}
		else if(allframes[0][9] + allframes[1][9] == 10)
		{	
			System.out.print(" | B");
		}
		
		printStartline();
   	  	   	  	
   	  	System.out.print("\t투구 ");
   	  	
		for(int lcount = 0; lcount < 10; lcount++)
		{
			System.out.print(" | ");
			if (allframes[0][lcount] == 10) {
				System.out.printf("%2s", printStrikeMark(allframes[0][lcount]));
				continue;
			}
			else System.out.printf("%s", printStrikeMark(allframes[0][lcount]));
			
			//2차투구
			if ( (allframes[0][lcount] < 10) && ((allframes[0][lcount] + allframes[1][lcount]) == 10) ) {
				System.out.printf("%s", "/");
				continue;
			}
		
			System.out.printf("%d", allframes[1][lcount]);   	  		
		}
   	  	
   	  	if(allframes[0][9] == 10)	
   	  	{
   	  		System.out.print(" | ");
   	  	
   	  		if(allframes[0][10] == 10) {
   	  			System.out.printf("%s", printStrikeMark(allframes[0][10]));
   	  			System.out.print(" | ");
   	  			System.out.printf("%s", printStrikeMark(allframes[0][11]));
   	  		}
   	  		else {
   	  			System.out.printf("%s", printStrikeMark(allframes[0][10]));
   	  			System.out.printf("%d", allframes[1][10]);
   	  		}
   	  	} else {
   	  		System.out.print(" | ");
   	  	
   	  		if(allframes[0][9] + allframes[1][9] == 10) {
   	  			System.out.printf("%s", printStrikeMark(allframes[0][10]));
   	  		}
   	  	}   	    	  	
 
   	    printStartline();
   	    System.out.print("\t점수 ");

   	    for(int jcount = 0; jcount < 10; jcount++)
   	    {
   	    	System.out.print(" |");
   	    	System.out.printf("%3d", scores[jcount]);
   	    }
   	    
   	    printStartPart();
	}

	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String s = "N";
		boolean chk_continue = false;
		
		start();
		
		do {
			
			chk_continue = false;
			init();
	
			//점수를 입력받는다.
			nextframe();
			
			//보너스 프레임 점수를 입력 받는다. 
			bonusframe();

			//결과를 집계한다.
			sumResult();
				    	
			//결과를 출력한다. 
			printResult();
			
			while(!chk_continue)
			{	
				try {
					System.out.print("\n\t다시 하시겠습니까? (Y/N)? ");
					s = scoreInput.next();
				
					if((s.toUpperCase().charAt(0) == 'Y') || (s.toUpperCase().charAt(0) == 'N'))
					{
						chk_continue = true;
					}else {
						throw new Exception("\n\t입력값을 확인하세요. ");
					}
				}
				catch (Exception ex) {
					System.out.print(ex.getMessage());
				}
			}
			
		} while(s.toUpperCase().charAt(0) == 'Y');
	}
		
}


