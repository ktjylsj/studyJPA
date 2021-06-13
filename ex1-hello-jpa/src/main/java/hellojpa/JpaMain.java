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

        // CREATE
        try{
            // 비영속
            /*Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");*/

            /*// 영속
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);

            // 동일성 비교 true
            System.out.println("result = " + (findMember1 == findMember2));*/

            // 영속
            /*Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);*/
            /*Member memberA = em.find(Member.class, 150L);

            memberA.setName("ZZZZZ");*/

            Member member = new Member(200L, "member2020");
            em.persist(member);

            em.flush();

            System.out.println("====================================");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        // READ
        /*try{
            // Member findMember = em.find(Member.class, 1L);
            // System.out.println("findMember.id = " + findMember.getId());
            // System.out.println("findMember.name = " + findMember.getName());

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                                .setFirstResult(1)      // 페이지네이션
                                .setMaxResult(10)
                                .getResultList();

            for(Member member : result){
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }*/

        // UPDATE
        /*try{
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloC");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }*/

        emf.close();
    }
}
