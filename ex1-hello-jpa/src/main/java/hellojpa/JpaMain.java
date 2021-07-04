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
            member.setTeam(team);           // 연관관계에서 주인에게 설정
            em.persist(member);

            // team.getMembers().add(member);
            // member.changeTeam(team);
            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
