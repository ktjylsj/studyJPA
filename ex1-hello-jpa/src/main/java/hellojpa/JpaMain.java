package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        // 웹 서버 실행 시 1회
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // == DB Connection ( 고객 요청 존재 시 만들었다가 쓰고 닫고, 쓰레드 공유 X )
        EntityManager em = emf.createEntityManager();

        // code 작성 위치
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            // member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            /*Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);*/
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            // 100번 팀이 있다는 가정 하에 사용
            // Team newTeam = em.find(Team.class, 100L);
            // findMember.setTeam(newTeam);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
