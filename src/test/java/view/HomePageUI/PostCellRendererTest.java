package view.HomePageUI;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PostCellRendererTest {
    @Test
    void renderer_ShouldHaveCorrectInitialSetup() {
        // Setup
        PostCellRenderer renderer = new PostCellRenderer();

        // Verify basic properties
        assertTrue(renderer.isOpaque());
        assertNotNull(renderer.getLayout());
        assertTrue(renderer.getLayout() instanceof BorderLayout);
    }
} 