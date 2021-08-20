package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.Blog;
import ci.gstoreplus.metier.Imetier;

public interface BlogMetier extends Imetier<Blog, Long>{
List<Blog> getBlocsFalse();
List<Blog> getBlocsTrue();

}
