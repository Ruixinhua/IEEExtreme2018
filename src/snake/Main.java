package snake;
//Don't place your source in a package
import java.util.*;
import java.lang.*;
import java.io.*;

//Please name your class Main
public class Main {
    class Position{
        int x,y;
        public Position(int a,int b) {
            x=a;
            y=b;
        }
    }
    class Snake{
        Position start;
        Position end;
        int s_index;
        int e_index;
        public Snake(Position s, Position e, int x_i,int y_i) {
            start = s;
            end = e;
            s_index = x_i;
            e_index = y_i;
        }
    }
    public static int XYtoIndex(int x,int y,int size){
        int index;
        if(y%2==1){
            index=(y-1)*size+x;
        }else{
            index=y*size-x+1;
        }
        return index;
    }
    class Player{
        Position position;
        int number;
        int index;//position index
        boolean win;
        public Player(Position p,int i,int n) {
            position = p;
            index = i;
            number = n;
        }
        public void moveTo(Position p) {
            position.x = p.x;
            position.y = p.y;
        }
    }

    public static void main (String[] args) throws java.lang.Exception {
        Main m = new Main();
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
       
        Position board[] = new Position[size*size+2];
        board[0] = m.new Position(0, 1);
        board[size*size+1] = m.new Position(0, size);
        
        for(int i = 0;i < size;i++) {
            for(int j = 0;j < size;j++) {
                if(i % 2 == 0) {
                    board[1+i*size+j] = m.new Position(j+1, i+1);
                }else {
                    board[1+i*size+j] = m.new Position(size-j, i+1);
                }
            }
        }
        
        int player_number = in.nextInt();
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < player_number; i++) {
            players.add(m.new Player(m.new Position(0, 1), 0, i+1));
        }
        int s_num = in.nextInt();
        Snake[] snakes = new Snake[board.length];
        for(int i=0;i<s_num;i++) {
            int x = in.nextInt(),y = in.nextInt();
            int x_end = in.nextInt(),y_end = in.nextInt();
            int s_index=XYtoIndex(x,y,size);
            int e_index=XYtoIndex(x_end,y_end,size);
            Position start = m.new Position(x, y);
            Position end = m.new Position(x_end, y_end);
            if(s_index<e_index){
                Position temp=start;
                start=end;
                end=temp;
                int t=s_index;
                s_index=e_index;
                e_index=t;
            }
            snakes[s_index] = m.new Snake(start, end,s_index,e_index);
        }
        int l_num = in.nextInt();
        for(int i=0;i<l_num;i++) {
            int x = in.nextInt(),y = in.nextInt();
            int x_end = in.nextInt(),y_end = in.nextInt();
            int s_index=XYtoIndex(x,y,size);
            int e_index=XYtoIndex(x_end,y_end,size);
            Position start = m.new Position(x, y);
            Position end = m.new Position(x_end, y_end);
            if(s_index>e_index) {
                Position temp = start;
                start = end;
                end = temp;
                int t = s_index;
                s_index = e_index;
                e_index = t;
            }
            snakes[s_index] = m.new Snake(start, end,s_index,e_index);
        }
        int steps[]=new int[in.nextInt()];
        for(int i=0;i<steps.length;i++) {
            steps[i] = in.nextInt()+in.nextInt();
        }
        Player winner[] = new Player[player_number];
        for (int i = 0; i < steps.length; i++) {
            if(players.size()==0) break;
            Player player = players.get(i%players.size());
            player.index+=steps[i];
            if(player.index >= board.length-1){
                player.win = true;
                winner[player.number-1] = player;
                players.remove(i%players.size());
            }else {
                Position des = board[player.index];
                player.moveTo(des);
                while(snakes[player.index] != null) {
                    player.moveTo(snakes[player.index].end);
                    player.index = snakes[player.index].e_index;
                }
            }
        }

        for(int i = 0;i < players.size();i++) {
            winner[players.get(i).number-1] = players.get(i);
        }
        for(int i = 0;i<winner.length;i++) {
            if(winner[i].win)
                System.out.println(winner[i].number+" "+"winner");
            else
                System.out.println(winner[i].number+" "+winner[i].position.x+" "+winner[i].position.y);
        }
        in.close();
    }
}