package lesson1;

import lesson1.obstacles.Obstacle;
import lesson1.obstacles.Track;
import lesson1.obstacles.Wall;
import lesson1.participant.Cat;
import lesson1.participant.Human;
import lesson1.participant.Participant;
import lesson1.participant.Robot;

public class Homework {
    public static void main(String[] args) {
        Human human = new Human("Pavel");
        Cat cat = new Cat("Barsik");
        Robot robot = new Robot("Terminator");

        Track track = new Track(150);
        Wall wall = new Wall(15);

        Participant[] participants = {human, cat, robot};
        Obstacle[] obstacles = {track, wall};

        for (Participant participant : participants) {
            for (Obstacle obstacle : obstacles) {
                if (!obstacle.passObstacleBy(participant)) {
                    System.out.printf("Участник %s ВЫБЫВАЕТ из соревнований %n", participant);
                    break;
                }
            }
        }
    }
}
