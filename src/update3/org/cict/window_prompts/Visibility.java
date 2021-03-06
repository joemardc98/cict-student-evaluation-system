/**
 * CAPSTONE PROJECT.
 * BSIT 4A-G1.
 * MONOSYNC TECHNOLOGIES.
 * MONOSYNC FRAMEWORK VERSION 1.0.0 TEACUP RICE ROLL.
 * THIS PROJECT IS PROPRIETARY AND CONFIDENTIAL ANY PART THEREOF.
 * COPYING AND DISTRIBUTION WITHOUT PERMISSION ARE NOT ALLOWED.
 *
 * COLLEGE OF INFORMATION AND COMMUNICATIONS TECHNOLOGY.
 * LINKED SYSTEM.
 *
 * PROJECT MANAGER: JHON MELVIN N. PERELLO
 * DEVELOPERS:
 * JOEMAR N. DELA CRUZ
 * GRETHEL EINSTEIN BERNARDINO
 *
 * OTHER LIBRARIES THAT ARE USED BELONGS TO THEIR RESPECTFUL OWNERS AND AUTHORS.
 * NO COPYRIGHT ARE INTENTIONAL OR INTENDED.
 * THIS PROJECT IS NOT PROFITABLE HENCE FOR EDUCATIONAL PURPOSES ONLY.
 * THIS PROJECT IS ONLY FOR COMPLIANCE TO OUR REQUIREMENTS.
 * THIS PROJECT DOES NOT INCLUDE DISTRIBUTION FOR OTHER PURPOSES.
 *
 */
package update3.org.cict.window_prompts;

import javafx.scene.layout.Pane;

/**
 *
 * @author Jhon Melvin
 */
public class Visibility {

    /**
     * Hide All Children.
     *
     * @param parent
     */
    public static void hideChildren(Pane parent) {
        if (parent == null) {
            return;
        }
        parent.getChildren().forEach(child -> {
            child.setVisible(false);
        });
    }

    /**
     * Show all children.
     * @param parent 
     */
    public static void showChildren(Pane parent) {
        if (parent == null) {
            return;
        }
        parent.getChildren().forEach(child -> {
            child.setVisible(true);
        });
    }
}
