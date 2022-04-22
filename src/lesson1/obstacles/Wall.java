package lesson1.obstacles;

import lesson1.participant.Participant;

public class Wall implements Obstacle{
    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean passObstacleBy(Participant participant) {
        if (participant.jump() > height) {
            System.out.printf("Участник %s успешно перепрыгнул через стену высотой %d метров %n", participant, height);
            return true;
        }
        System.out.printf("Участник %s НЕ СМОГ ПЕРЕПРЫГНУТЬ через стену высотой %d метров %n", participant, height);
        return false;
    }
}
