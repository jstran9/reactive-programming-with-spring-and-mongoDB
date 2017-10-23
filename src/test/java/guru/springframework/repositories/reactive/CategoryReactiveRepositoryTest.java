package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    public static final String DESCRIPTION = "description";
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() throws Exception {
        Category category = new Category();

        category.setDescription("hey");

        categoryReactiveRepository.save(category).block();

        assertEquals(Long.valueOf(1L), categoryReactiveRepository.count().block());
    }

    @Test
    public void testFindByDescription() throws Exception {
        Category category = new Category();
        category.setDescription(DESCRIPTION);

        categoryReactiveRepository.save(category).then().block();

        Category categoryId = categoryReactiveRepository.findByDescription(DESCRIPTION).block();

        assertNotNull(categoryId.getId());

    }

}