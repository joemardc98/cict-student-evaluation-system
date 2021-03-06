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
package update3.org.subjectselector;

import app.lazy.models.DB;
import app.lazy.models.Database;
import app.lazy.models.SubjectMapping;
import com.melvin.mono.fx.MonoLauncher;
import com.melvin.mono.fx.events.MonoClick;
import com.jfoenix.controls.JFXButton;
import com.jhmvin.Mono;
import com.jhmvin.fx.async.Transaction;
import com.jhmvin.fx.controls.simpletable.SimpleTable;
import com.jhmvin.fx.controls.simpletable.SimpleTableRow;
import com.melvin.mono.fx.bootstrap.M;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jhon Melvin
 */
public class SubjectSelector extends MonoLauncher {

    @FXML
    private VBox application_root;

    @FXML
    private TextField txt_search;

    @FXML
    private JFXButton btn_search;

    @FXML
    private JFXButton btn_cancel;

    @FXML
    private VBox vbox_table;

    @Override
    public void onStartUp() {
        MonoClick.addClickEvent(btn_cancel, () -> {
            this.close();
        });
        MonoClick.addClickEvent(btn_search, () -> {
            if (this.txt_search.getText().isEmpty()) {
                this.fetchAllSubjects();
            } else {
                this.searchSubject();
            }
        });
    }

    @Override
    public void onDelayedStart() {
        fetchAllSubjects();
    }

    private void fetchAllSubjects() {
        FetchSubjects allTx = new FetchSubjects();
        allTx.whenStarted(() -> {
            this.btn_search.setDisable(true);
            this.setCursor(Cursor.WAIT);
        });
        allTx.whenCancelled(() -> {
        });
        allTx.whenFailed(() -> {
        });
        allTx.whenSuccess(() -> {
            createTable(allTx.getAllSubjects());
        });
        allTx.whenFinished(() -> {
            this.btn_search.setDisable(false);
            this.setCursor(Cursor.DEFAULT);
        });
        allTx.transact();
    }

    private void searchSubject() {
        SearchSubject allTx = new SearchSubject();
        allTx.setSearchValue(txt_search.getText().trim());
        allTx.whenStarted(() -> {
            this.btn_search.setDisable(true);
            this.setCursor(Cursor.WAIT);
        });
        allTx.whenCancelled(() -> {
        });
        allTx.whenFailed(() -> {
        });
        allTx.whenSuccess(() -> {
            createTable(allTx.getAllSubjects());
        });
        allTx.whenFinished(() -> {
            this.btn_search.setDisable(false);
            this.setCursor(Cursor.DEFAULT);
        });
        allTx.transact();
    }

    private void createTable(ArrayList<SubjectMapping> list) {
        if (list == null) {
            list = new ArrayList<SubjectMapping>();
        }
        SimpleTable tbl_faculty = new SimpleTable();
        for (SubjectMapping map : list) {

            RowSubject rowController = M.load(RowSubject.class);
            Pane rowSubject = rowController.getApplicationRoot();
            rowController.getLbl_code().setText(map.getCode());
            rowController.getLbl_title().setText(map.getDescriptive_title());
            rowController.getLbl_id().setText("ID " + map.getId());
            SimpleTableRow row = SimpleTable.fxRow(rowSubject, 100.0);
            row.getRowMetaData().put("MAP", map);
            MonoClick.addClickEvent(row, ()->{
                subject = (SubjectMapping) row.getRowMetaData().get("MAP");
                Mono.fx().getParentStage(btn_cancel).close();
            });
            
            // add to table
            tbl_faculty.addRow(row);
        }
        
        SimpleTable.fxTable(tbl_faculty, vbox_table);

    }
    
    private SubjectMapping subject;
    public SubjectMapping getSubjectSelected() {
        return subject;
    }

    class SearchSubject extends FetchSubjects {

        private String searchValue;

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        @Override
        protected boolean transaction() {
            this.allSubjects = Mono.orm()
                    .newSearch(Database.connect().subject())
                    .likely(DB.subject().code, searchValue, "")
                    .active()
                    .all();
            return true;
        }

    }

    class FetchSubjects extends Transaction {

        protected ArrayList<SubjectMapping> allSubjects;

        public ArrayList<SubjectMapping> getAllSubjects() {
            return allSubjects;
        }

        @Override
        protected boolean transaction() {
            allSubjects = Mono.orm()
                    .newSearch(Database.connect().subject())
                    .active()
                    .all();

            return true;
        }

        @Override
        protected void after() {

        }

    }

}
