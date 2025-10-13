package com.example;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class connect {
    private static SqlSessionFactory sqlSessionFactory;
     // 비동기 처리
    // private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final ExecutorService executor = Executors.newCachedThreadPool();

        static {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("Error : " + e.getMessage(), e);
        }
    }

    // public <T> List<T> getList() { 
    //     return new ArrayList<T>(); 
    // }

    public String sqlFutureSelect(String namespace, String id) throws Exception {
        try{
            String statementId = namespace + "." + id;
            Future<String> result = executor.submit(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    return session.selectOne(statementId);
                }   catch (Exception e) {
                    return null;
                }
            });
            // session.close();
            // 동기로 전환되어서 안됨
            return result.get();    
        }catch(Exception e){
            return null;
        }
    }

    public String sqlCompletableFutureSelect(String namespace, String id) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    return session.selectOne(statementId);
                }   catch (Exception e) {
                    return null;
                }
            }, executor);
            // session.close();
            // 동기로 전환되어서 안됨
            return result.get();    
        }catch(Exception e){
            return null;
        }
    }

    public String sqlCompletableFutureSelect(String namespace, String id, Object param) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    return session.selectOne(statementId, param);
                }   catch (Exception e) {
                    return null;
                }
            }, executor);
            // session.close();
            // 동기로 전환되어서 안됨
            return result.get();    
        }catch(Exception e){
            return null;
        }
    }

    public <T> List<T> sqlCompletableFutureSelectList(String namespace, String id) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture<List<T>> result = CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    return session.selectList(statementId);
                }   catch (Exception e) {
                    return null;
                }
            }, executor);
            // session.close();
            // 동기로 전환되어서 안됨
            return result.get();    
        }catch(Exception e){
            return null;
        }
    }

    public <T> List<T> sqlCompletableFutureSelectList(String namespace, String id, Object param) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture<List<T>> result = CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    return session.selectList(statementId, param);
                }   catch (Exception e) {
                    return null;
                }
            }, executor);
            // session.close();
            // 동기로 전환되어서 안됨
            return result.get();    
        }catch(Exception e){
            return null;
        }
    }

    public int sqlInsert(String namespace, String id) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    int updateResult = session.insert(statementId);
                    return updateResult;
                }   catch (Exception e) {
                    return null;
                }
            }, executor);
            result.thenAccept(updateResult -> {
                System.out.println("업데이트 완료 되었습니다.");
            });
            return result.get();
        }catch(Exception e){
            return 0;
        }
    }

    public int sqlInsert(String namespace, String id, Object param) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    int updateResult = session.insert(statementId, param);
                    return updateResult;
                }   catch (Exception e) {
                    return null;
                }
            }, executor);
            result.thenAccept(updateResult -> {
                System.out.println("업데이트 완료 되었습니다.");
            });
            return result.get();
        }catch(Exception e){
            return 0;
        }
    }

    public int sqlUpdate(String namespace, String id) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    int updateResult = session.update(statementId);
                    return updateResult;
                }   catch (Exception e) {
                    return null;
                }
            }, executor);
            result.thenAccept(updateResult -> {
                System.out.println("업데이트 완료 되었습니다.");
            });
            return result.get();
        }catch(Exception e){
            return 0;
        }
    }

    public int sqlUpdate(String namespace, String id, Object param) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    int updateResult = session.update(statementId, param);
                    return updateResult;
                }   catch (Exception e) {
                    return null;
                }
            }, executor);
            result.thenAccept(updateResult -> {
                System.out.println("업데이트 완료 되었습니다.");
            });
            return result.get();
        }catch(Exception e){
            return 0;
        }
    }

    public void sqlDelete(String namespace, String id) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    int deletedCount  = session.delete(statementId);
                    return deletedCount;
                }   catch (Exception e) {
                    return null;
                }
            }, executor).thenAccept(deletedCount -> {
                System.out.println("삭제 완료 되었습니다.");
            });
        }catch(Exception e){
        }
    }

    public void sqlDelete(String namespace, String id, Object param) throws Exception {
        try{
            String statementId = namespace + "." + id;
            
            CompletableFuture.supplyAsync(() -> {
                try(SqlSession session = sqlSessionFactory.openSession()) {
                    int deletedCount  = session.delete(statementId, param);
                    return deletedCount;
                }   catch (Exception e) {
                    return null;
                }
            }, executor).thenAccept(deletedCount -> {
                System.out.println("삭제 완료 되었습니다.");
            });
        }catch(Exception e){
        }
    }

    public void shutDown() {
        // 나중에 다시 만들기
        executor.shutdown();        
        // shutdownNow(); 강제 종료
    }

}
