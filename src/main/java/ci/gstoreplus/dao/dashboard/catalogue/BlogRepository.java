package ci.gstoreplus.dao.dashboard.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>{

}
