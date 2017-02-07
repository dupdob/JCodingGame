package CommunityGames;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class WinamaxGolf {

    public static class Coord {
        protected int x;
        protected int y;

        public Coord(int x, int y) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    public static class Map {
        private String[] map;

        public Map(String[] data) {
            this.map = new String[data.length];
            for (int i = 0; i < data.length; i++) {
                this.map[i] = data[i];
            }
        }

        public Map(int width, int heigh){
            this.map = new String[heigh];
            for (int i = 0; i < this.map.length; i++) {
                char[] chars = new char[width];
                Arrays.fill(chars, '.');
                this.map[i] = new String(chars);
            }
        }

        public Map mergeWith(Map other){
            String[] result = new  String[this.map.length];
            for (int i = 0; i < result.length; i++) {
                String a =this.map[i];
                String b = other.map[i];
                char[] res = new char[a.length()];
                for (int j = 0; j < a.length(); j++) {
                    char carA = a.charAt(j);
                    char carB = b.charAt(j);
                    if ((carA=='*' || carB =='*')) {
                        if (carB=='*' && carA=='*') {
                            return null;
                        }
                        res[j]='*';
                    } else if (carA=='.' || carA=='X'){
                        res[j] = carB;
                    } else if (carB == '.' || carB=='X'){
                        res[j] = carA;
                    } else if (carA==carB){
                        res[j] = carA;
                    }
                    else {
                        return null;
                    }
                }
                result[i] = new String(res);
            }
            return new Map(result);
        }

        public Map clean(){
            String[] result = new  String[this.map.length];
            for (int i = 0; i < result.length; i++) {
                String a =this.map[i];
                char[] res = new char[a.length()];
                for (int j = 0; j < a.length(); j++) {
                    char carA = a.charAt(j);
                    if ((carA=='*' || carA =='X')) {
                        res[j]='.';
                    } else {
                        res[j] = carA;
                    }
                }
                result[i] = new String(res);
            }
            return new Map(result);
        }

        public Map duplicate(){
            return new Map(this.map);
        }

        public char get(Coord coord) {
            return this.map[coord.getY()].charAt(coord.getX());
        }

        public void set(Coord coord, char car){
            set(coord.x, coord.y, car);
        }

        public void set(int x, int y, char car){
            char[] temp = map[y].toCharArray();
            temp[x] = car;
            map[y] = String.valueOf(temp);

        }

        public Ball moveUp(Ball from, int steps) {
            char filler = '^';
            ArrayList<Coord> path = new ArrayList<>(steps);
            for (int i = 0; i <= steps; i++) {
                int y = from.y -i;
                if (y<0)
                    return null;
                path.add(new Coord(from.x, y));
            }
            return scanPath(steps, path, filler);
        }

        public Ball moveDown(Ball from, int steps) {
            char filler = 'v';
            ArrayList<Coord> path = new ArrayList<>(steps);
            for (int i = 0; i <= steps; i++) {
                int y = from.y +i;
                if (y>=map.length)
                    return null;
                path.add(new Coord(from.x, y));
            }
            return scanPath(steps, path, filler);
        }

        public Ball moveLeft(Ball from, int steps) {
            char filler = '<';
            ArrayList<Coord> path = new ArrayList<>(steps);
            for (int i = 0; i <= steps; i++) {
                int x = from.x -i;
                if (x<0)
                    return null;
                path.add(new Coord(x, from.y));
            }
            return scanPath(steps, path, filler);
        }

        public Ball moveRight(Ball from, int steps) {
            char filler = '>';
            ArrayList<Coord> path = new ArrayList<>(steps);
            for (int i = 0; i <= steps; i++) {
                int x = from.x +i;
                if (x>=map[0].length())
                    return null;
                path.add(new Coord(x, from.y));
            }
            return scanPath(steps, path, filler);
        }

        private Ball scanPath(int steps, ArrayList<Coord> path, char filler) {
            for (int i = 0; i < path.size(); i++) {
                switch (get(path.get(i))){
                    case 'X':
                        if (i==path.size()-1)
                            // can't land on water
                            return null;
                    case '.':
                    case 'H':
                        break;
                    default:
                        return null;
                }
            }
            // if we are here, no trajectory issue
            for (int i = 0; i < path.size()-1; i++) {
                set(path.get(i), filler);
            }

            Ball ret= new Ball(path.get(path.size()-1), steps -1);
            if (get(ret) == 'H') {
                set(ret.x, ret.y, '*');
                ret.inHole();
            }
            else if (steps==1) {
                // ball should have reached hole
                return null;
            }
            return ret;
        }

        public void dump() {
            for (int i = 0; i < this.map.length; i++) {
                System.err.println(this.map[i]);
            }
        }

        public void print() {
            for (int i = 0; i < this.map.length; i++) {
                System.out.println(this.map[i]);
            }
        }

    }

    static class Ball extends Coord {
        private int round;

        public Ball(int x, int y, int round) {
            super(x, y);
            this.round = round;
        }

        public Ball(Coord coord, int round) {
            super(coord.x, coord.y);
            this.round = round;
        }

        public void inHole(){
            round = 0;
        }

        public LinkedList<Map> findSolutions(Map initMap) {
            LinkedList<Map>   maps = new LinkedList<>();
            if (round==0)
            {
                maps.add(initMap);
                return maps;
            }
            Map temp;
            Ball move;
            temp = initMap.duplicate();
            move = temp.moveUp(this, round);
            if (move != null) {
                maps.addAll(move.findSolutions(temp));
            }
            temp = initMap.duplicate();
            move = temp.moveDown(this, round);
            if (move != null) {
                maps.addAll(move.findSolutions(temp));
            }
            temp = initMap.duplicate();
            move = temp.moveLeft(this, round);
            if (move != null) {
                maps.addAll(move.findSolutions(temp));
            }
            temp = initMap.duplicate();
            move = temp.moveRight(this, round);
            if (move != null) {
                maps.addAll(move.findSolutions(temp));
            }
            return maps;
        }

    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[] map;
        ArrayList<Ball> balls = new ArrayList<>();
        int width = in.nextInt();
        int height = in.nextInt();
        map = new String[height];
        for (int i = 0; i < height; i++) {
            String line = in.next();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c >'0' && c <='9')
                {
                    // found a ball
                    balls.add(new Ball(j, i, (c -'0')));
                    System.err.format("Ball at %d,%d (round %d)%n", j, i, (c-'0'));
                    char[] temp = line.toCharArray();
                    temp[j] ='.';
                    line = String.valueOf(temp);
                }
            }
            map[i] = line;
        }

        Map initMap = new Map(map);
        initMap.dump();
        LinkedList<LinkedList<Map>> fullSolutions= new LinkedList<>();
        for (int i = 0; i < balls.size(); i++) {
            LinkedList<Map> solutions = balls.get(i).findSolutions(initMap);
            fullSolutions.add(solutions);
        }

        Map solution = new Map(width, height);

//        System.err.println("*Solution*");
        for (int i = 0; i < fullSolutions.size(); i++) {
            LinkedList<Map> solutionBlock = fullSolutions.get(i);
            if (solutionBlock.size()==1){
                Map temp = solution.mergeWith(solutionBlock.get(0));
                if (temp==null){
                    // no solution possible
                    System.err.println("Cant merge maps");
                    System.err.println("--------------");
                    solution.dump();
                    System.err.println("--------------");
                    solutionBlock.get(0).dump();
                    System.err.println("--------------");
                } else {
                    solution = temp;
                }
                fullSolutions.remove(i--);
            }
        }

        resolve(solution, fullSolutions, 0).clean().print();
    }

    private static Map resolve(Map solution, LinkedList<LinkedList<Map>> fullSolutions, int i) {
        if (i>=fullSolutions.size())
            return solution;
        LinkedList<Map> locals = fullSolutions.get(i);
        for (int j = 0; j <locals.size(); j++) {
            Map temp = solution.mergeWith(locals.get(j));
            if (temp!=null){
                temp = resolve(temp, fullSolutions, i+1);
                if (temp!=null)
                    return temp;
            }
        }
        return null;
    }

}