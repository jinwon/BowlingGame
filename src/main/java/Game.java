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
	private static int scores[] = new int[10];
	
	//전체 점수 보관용 
	private static int allframes[][] = new int[2][12];
		
	public static int[] getScores() {
		return scores;
	}

	public static void setScores(int scores[]) {
		Game.scores = scores;
	}

	public static int[][] getAllframes() {
		return allframes;
	}

	public static void setAllframes(int allframes[][]) {
		Game.allframes = allframes;
	}
	
	
	//시작 메시지 
	public static void start() {
		System.out.println("\n\t  볼링 점수 계산기 ");		
	}

	// 초기화 
	public static void init() {
		try {
			for(int l1 = 0; l1 < 10; l1++) getScores()[l1] = 0;
			for(int l2 = 0; l2 < 12; l2++) getAllframes()[0][l2] = getAllframes()[1][l2] = 0;							
		}
		catch (IndexOutOfBoundsException ex)
		{
			System.out.println("초기값 지정 오류가 발생하였습니다. ");
		}
	}
	
	//프레임 첫 접수를 입력한다.
	public static void insertFirstDrow(int frame, int score) throws Exception {
		// TODO Auto-generated method stub
		// 프레임은 0부터 시작한다.
		
		try {
			if ((frame < 0) && (frame > 11)) throw new Exception("\n\t입력값을 확인하세요. ");
			
			if ((score < 0) && (score > 10)) throw new Exception("\n\t입력값을 확인하세요. ");
			
			getAllframes()[0][frame] = score;
		}
		catch (IndexOutOfBoundsException ex)
		{
			System.out.println(" 저장시 오류가 발생하였습니다. ");
		}
	}

	//프레임 두번째 접수를 입력한다.
	public static void insertSecondDrow(int frame, int score) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			if ((frame < 0) && (frame > 11)) throw new Exception("\n\t입력값을 확인하세요. ");
			
			if ((score < 0) && (score > 10)) throw new Exception("\n\t입력값을 확인하세요. ");
			
			getAllframes()[1][frame] = score;
		}
		catch (IndexOutOfBoundsException ex)
		{
			System.out.println(" 저장시 오류가 발생하였습니다. ");
		}	
	}
	
	//프레임 접수를 입력한다.
	public static void insertDrow(int frame, int times, int score) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			if ((times < 0) && (times > 1)) throw new Exception("\n\t입력값을 확인하세요. ");			
			if ((frame < 0) && (frame > 11)) throw new Exception("\n\t입력값을 확인하세요. ");			
			if ((score < 0) && (score > 10)) throw new Exception("\n\t입력값을 확인하세요. ");
			
			getAllframes()[times][frame] = score;
		}
		catch (IndexOutOfBoundsException ex)
		{
			System.out.println(" 저장시 오류가 발생하였습니다. ");
		}	
	}	
	
	// 합계 점수를 가져옴 
	public static int getScores(int index) throws Exception{
		// TODO Auto-generated method stub
		
		try {
			if ((index < 0 ) && (index > 10)) throw new Exception("");
		}
		catch (Exception ex) {
			System.out.println(" 잘못된 범위입니다. ");
		}
		
		return scores[index];
	}
	
	// 프레임 시작 
	public static void nextframe() throws Exception {
		// 투구 번호 
		int times = 0;

		for(int i = 0; i < 10; i++) 
		{
			boolean chk = false;
			int Pins = 0;
			
			times = 0;

			while(!chk) {
			    try
			    {				    	
					System.out.printf("\n\t 현재 프레임 %2d", (i + 1));
				    System.out.printf("\n\t 투구 %d 점수를 입력하세요: ", (times+1));

			    	Pins = scoreInput.nextInt();
			    
			 		if(Pins <= 10 && Pins >= 0) {
			 			insertDrow(i, times, Pins);
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
				// 투구 번호 
				times = 1;

				if(chk) break;
				
				try {
					System.out.printf("\n\t 현재 프레임 %2d", (i + 1));   
					System.out.printf("\n\t 투구 %d 점수를 입력하세요: ", (times+1));
					Pins2 = scoreInput.nextInt();

					// 두번째 값을 검증한다.
					if(Pins2 <= 10 && Pins2 >= 0 && Pins2 + getAllframes()[0][i] < 11) 
					{
						insertDrow(i, times, Pins2);

						if(Pins2 + getAllframes()[0][i] == 10) printSpare();
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
	private static void bonusframe() throws Exception {
		// TODO Auto-generated method stub

		// 투구 번호 
		int times = 0;

		//10 프레임에서 스트라이크 		
		if(getAllframes()[0][9] == 10) 
		{
			boolean chk_error1 = false;
			int bonus1 = 0;
			
			times = 0;
			
		    while(!chk_error1) 
		    {
		    	try{
			    	System.out.printf("\n\t 현재 프레임 보너스1\n\t 투구%d 점수를 입력하세요 : ", (times+1));
			    	bonus1 = scoreInput.nextInt();
	
			    	if(bonus1 < 11 && bonus1 >= 0) {
			    		
			    		insertDrow(10, times, bonus1);
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
		    if(getAllframes()[0][10] == 10) 
		    {
		    	boolean chk_error2 = false;
		    	int bonus2 = 0;

		    	times = 0;	
		    	
		    	printStrike();

		    	if(getAllframes()[0][11] == 10) printStrike();

		    	 while(!chk_error2) {

		    		try{
						System.out.printf("\n\t 현재 프레임 보너스2\n\t 투구%d 점수를 입력하세요: ", (times+1));
						bonus2 = scoreInput.nextInt();
						
						if(bonus2 < 11 && bonus2 > 0) {
							insertDrow(11, times, bonus2);

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
		    	times = 1;

		    	while(!chk_error3) {
		    		
		    		try {
			    		System.out.printf("\n\t 현재 프레임 보너스1\n\t 투구%d 점수를 입력하세요: ", (times+1));
			    		bonus3 = scoreInput.nextInt();
	
			    		if(bonus3 < 11 && bonus3 >= 0 && bonus3 + getAllframes()[0][10] < 11)
			    		{
			    			insertDrow(10, times, bonus3);
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
		else if(getAllframes()[0][9] + getAllframes()[1][9] == 10)
		{
			//10 프레임에서 스페어 처리  			
			boolean chk_error4 = false;

			int bonus4 = 0;
			
			times = 0;

			while(!chk_error4){
				try {
					System.out.printf("\n\t 현재 프레임 보너스\n\t 투구%d 점수를 입력하세요: ", (times+1));
					bonus4 = scoreInput.nextInt();
	
					if(bonus4 <= 10 && bonus4 >= 0){
						
						insertDrow(10, times, bonus4);
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
		if(getAllframes()[0][0] + getAllframes()[1][0] == 10)
			getScores()[0] = 10 + getAllframes()[0][1];
		else
			getScores()[0] = getAllframes()[0][0] + getAllframes()[1][0];
		
		//스트라이크 처리시 
		if(getAllframes()[0][0] == 10) {
			if(getAllframes()[0][1] == 10)
				getScores()[0] = 20 + getAllframes()[0][2];
			else
				getScores()[0] = 10 + getAllframes()[0][1] + getAllframes()[1][1];
		}

		//2번째부터 10번째의 프레임 계산 	
		for(int j = 1; j < 10; j++) 
		{
			//스트라이크 처리시.
			if(getAllframes()[0][j] == 10)
			{
				//연속 스트라이크 처리시.	
				if(getAllframes()[0][j + 1] == 10) getScores()[j] = getScores()[j - 1] + 20 + getAllframes()[0][j + 2];
				else getScores()[j] = getScores()[j - 1] + 10 + getAllframes()[0][j + 1] + getAllframes()[1][j + 1];
				continue;
			 }
		
			//스패어 처리시. 
			if(getAllframes()[0][j] + getAllframes()[1][j] == 10) {
				getScores()[j] = getScores()[j - 1] + 10 + getAllframes()[0][j + 1];
			} else {
				getScores()[j] = getScores()[j - 1] + getAllframes()[0][j] + getAllframes()[1][j];
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
			if(getAllframes()[0][9] == 10) 
			{
				if(getAllframes()[0][10] == 10) System.out.print("  B1 B2");
				else System.out.print("  B");
			}
			else if(getAllframes()[0][9] + getAllframes()[1][9] == 10)
			{	
				System.out.print("  B");
			}
		}
		
		printStartline();
		System.out.print("\t투구1 ");
		
		for(int l = 0; l < curframe; l++)
		{
			if (l >= 10 ) break;
			System.out.printf("%4d", getAllframes()[0][l]);
		}

		if (curframe == 11)
		{
			if(getAllframes()[0][9] == 10)	
			{
				if(getAllframes()[0][10] == 10) System.out.printf("%4d%4d", getAllframes()[0][10], getAllframes()[0][11]);
				else System.out.printf("%4d", getAllframes()[0][10]);	
			} else {
				if(getAllframes()[0][9] + getAllframes()[1][9] == 10) System.out.printf("%4d", getAllframes()[0][10]);
			}
		}
		
		printStartline();   	  	
		System.out.print("\t투구2 ");
		
		
		for(int i1 = 0; i1 < vframe; i1++) {
			if (i1 > 10 ) break;
			System.out.printf("%4d", getAllframes()[1][i1]);
		}
		
		if(getAllframes()[0][9] == 10 && getAllframes()[0][10] != 10)
			System.out.printf("%4d", getAllframes()[1][10]);
		
		printStartline();
		System.out.print("\t점수 ");
		
		for(int j1 = 0; j1 < vframe; j1++) {
			if (j1 > 10 ) break;
			System.out.printf("%4d", getScores()[j1]);
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
		
		if(getAllframes()[0][9] == 10) 
		{
			if(getAllframes()[0][10] == 10) System.out.print(" | B1 | B2");
			else System.out.print(" | B");
		}
		else if(getAllframes()[0][9] + getAllframes()[1][9] == 10)
		{	
			System.out.print(" | B");
		}
		
		printStartline();
   	  	   	  	
   	  	System.out.print("\t투구 ");
   	  	
		for(int lcount = 0; lcount < 10; lcount++)
		{
			System.out.print(" | ");
			if (getAllframes()[0][lcount] == 10) {
				System.out.printf("%2s", printStrikeMark(getAllframes()[0][lcount]));
				continue;
			}
			else System.out.printf("%s", printStrikeMark(getAllframes()[0][lcount]));
			
			//2차투구
			if ( (getAllframes()[0][lcount] < 10) && ((getAllframes()[0][lcount] + getAllframes()[1][lcount]) == 10) ) {
				System.out.printf("%s", "/");
				continue;
			}
		
			System.out.printf("%d", getAllframes()[1][lcount]);   	  		
		}
   	  	
   	  	if(getAllframes()[0][9] == 10)	
   	  	{
   	  		System.out.print(" | ");
   	  	
   	  		if(getAllframes()[0][10] == 10) {
   	  			System.out.printf("%s", printStrikeMark(getAllframes()[0][10]));
   	  			System.out.print(" | ");
   	  			System.out.printf("%s", printStrikeMark(getAllframes()[0][11]));
   	  		}
   	  		else {
   	  			System.out.printf("%s", printStrikeMark(getAllframes()[0][10]));
   	  			System.out.printf("%d", getAllframes()[1][10]);
   	  		}
   	  	} else {
   	  		System.out.print(" | ");
   	  	
   	  		if(getAllframes()[0][9] + getAllframes()[1][9] == 10) {
   	  			System.out.printf("%s", printStrikeMark(getAllframes()[0][10]));
   	  		}
   	  	}   	    	  	
 
   	    printStartline();
   	    System.out.print("\t점수 ");

   	    for(int jcount = 0; jcount < 10; jcount++)
   	    {
   	    	System.out.print(" |");
   	    	System.out.printf("%3d", getScores()[jcount]);
   	    }
   	    
   	    printStartPart();
	}

	
	
	public static void main(String[] args) throws Exception {
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


