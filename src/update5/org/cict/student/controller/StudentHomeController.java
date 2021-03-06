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
 * JOEMAR N. DE LA CRUZ
 * GRETHEL EINSTEIN BERNARDINO
 *
 * OTHER LIBRARIES THAT ARE USED BELONGS TO THEIR RESPECTFUL OWNERS AND AUTHORS.
 * NO COPYRIGHT ARE INTENTIONAL OR INTENDED.
 * THIS PROJECT IS NOT PROFITABLE HENCE FOR EDUCATIONAL PURPOSES ONLY.
 * THIS PROJECT IS ONLY FOR COMPLIANCE TO OUR REQUIREMENTS.
 * THIS PROJECT DOES NOT INCLUDE DISTRIBUTION FOR OTHER PURPOSES.
 *
 */
package update5.org.cict.student.controller;

import app.lazy.models.AcademicProgramMapping;
import app.lazy.models.AcademicTermMapping;
import app.lazy.models.CurriculumMapping;
import app.lazy.models.DB;
import app.lazy.models.Database;
import app.lazy.models.StudentMapping;
import artifacts.MonoString;
import com.itextpdf.text.Document;
import com.jfoenix.controls.JFXButton;
import com.jhmvin.Mono;
import com.jhmvin.fx.async.Transaction;
import com.jhmvin.fx.controls.simpletable.SimpleTable;
import com.jhmvin.fx.controls.simpletable.SimpleTableCell;
import com.jhmvin.fx.controls.simpletable.SimpleTableRow;
import com.jhmvin.fx.controls.simpletable.SimpleTableView;
import com.jhmvin.fx.display.ControllerFX;
import com.jhmvin.fx.display.LayoutDataFX;
import com.jhmvin.fx.display.SceneFX;
import com.jhmvin.orm.SQL;
import com.jhmvin.orm.Searcher;
import com.jhmvin.transitions.Animate;
import com.melvin.mono.fx.bootstrap.M;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.text.WordUtils;
import org.cict.authentication.authenticator.SystemProperties;
import org.cict.reports.ReportsUtility;
import org.cict.reports.result.PrintResult;
import org.controlsfx.control.Notifications;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import update.org.cict.controller.home.Home;
import update5.org.cict.student.layout.CreateNewStudent;

/**
 *
 * @author Joemar
 */
public class StudentHomeController extends SceneFX implements ControllerFX {

    @FXML
    private StackPane application_root;

    @FXML
    private JFXButton btn_home;

    @FXML
    private ComboBox<String> cmb_category_main;

    @FXML
    private TextField txt_search_key_main;

    @FXML
    private JFXButton btn_search1_main;

    @FXML
    private CheckBox chkbx_onlyEnrolled_main;

    @FXML
    private ComboBox<String> cmb_category;

    @FXML
    private JFXButton btn_home1;

    @FXML
    private TextField txt_search_key;

    @FXML
    private JFXButton btn_search;

    @FXML
    private CheckBox chkbx_onlyEnrolled;

    @FXML
    private VBox vbox_result;

    @FXML
    private VBox vbox_list;

    @FXML
    private VBox vbox_no_result;

    @FXML
    private VBox vbox_home;

    @FXML
    private Label lbl_status;

    @FXML
    private Label lbl_status1;

    @FXML
    private JFXButton btn_print;

    @FXML
    private JFXButton btn_new_student;
            
    @FXML
    private JFXButton btn_new_student1;
            
    @Override
    public void onInitialization() {
        super.bindScene(application_root);
        vbox_home.setVisible(false);
        vbox_result.setVisible(false);
        Animate.fade(vbox_home, 150, () -> {
            vbox_result.setVisible(false);
            vbox_home.setVisible(true);
        }, vbox_home, vbox_result);
        this.setComboBox();
    }
    private final String NAME = "NAME", STUDENT_NUM = "STUDENT NUMBER", ACAD_PROG = "ACADEMIC PROGRAM";
    private String SELECTED_mode = this.STUDENT_NUM;
    private Boolean isEnrolledSelected = false;

    @Override
    public void onEventHandling() {
        chkbx_onlyEnrolled_main.selectedProperty().addListener((g) -> {
            if (chkbx_onlyEnrolled_main.isSelected()) {
                isEnrolledSelected = true;
            } else {
                isEnrolledSelected = false;
            }
            chkbx_onlyEnrolled.selectedProperty().setValue(isEnrolledSelected);
        });
        chkbx_onlyEnrolled.selectedProperty().addListener((g) -> {
            if (chkbx_onlyEnrolled.isSelected()) {
                isEnrolledSelected = true;
            } else {
                isEnrolledSelected = false;
            }
            isFiltered = false;
            chkbx_onlyEnrolled_main.selectedProperty().setValue(isEnrolledSelected);
        });
        super.addClickEvent(btn_home, () -> {
            Home.callHome(this);
        });
        super.addClickEvent(btn_home1, () -> {
            vbox_result.setVisible(false);
            changeHome(vbox_home);
        });
        cmb_category_main.valueProperty().addListener((a) -> {
            SELECTED_mode = cmb_category_main.getSelectionModel().getSelectedItem();
            cmb_category.getSelectionModel().select(SELECTED_mode);
        });
        cmb_category.valueProperty().addListener((a) -> {
            SELECTED_mode = cmb_category.getSelectionModel().getSelectedItem();
            cmb_category_main.getSelectionModel().select(SELECTED_mode);
        });
        super.addClickEvent(btn_search1_main, () -> {
            onSearch();
        });
        super.addClickEvent(btn_search, () -> {
            onSearch();
        });
        Mono.fx().key(KeyCode.ENTER).release(application_root, () -> {
            onSearch();
        });
        super.addClickEvent(btn_print, () -> {
            this.printResult();
        });
        super.addClickEvent(btn_new_student, ()->{
            this.addNewStudent();
        });
        super.addClickEvent(btn_new_student1, ()->{
            this.addNewStudent();
        });
    }
    
    private void addNewStudent() {
        CreateNewStudent curriculumChooser = M.load(CreateNewStudent.class);
        curriculumChooser.onDelayedStart(); // do not put database transactions on startUp
        //----------------------------------------------------------------------
        try {
            curriculumChooser.getCurrentStage().show();
        } catch (NullPointerException e) {
            Stage a = curriculumChooser.createChildStage(super.getStage());
            a.initStyle(StageStyle.UNDECORATED);
            a.show();
        }
    }

    private boolean isFiltered = false;
    private String previousReportDetail;
    private void printResult() {
        if (students == null || students.isEmpty()) {
            Notifications.create()
                    .title("No Result")
                    .text("No result found to print.")
                    .showWarning();
            return;
        }
        Document doc = ReportsUtility.paperSizeChooser(this.getStage());
        if(doc==null) {
            return;
        }
        String[] colNames = new String[]{"Student Number", "Last Name", "First Name", "Middle Name", "Section"};
        ArrayList<String[]> rowData = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            StudentMapping result = students.get(i);
            StudentInformation info = new StudentInformation(result);
            String[] row = new String[]{(i + 1) + ".  " + result.getId(),
                WordUtils.capitalizeFully(info.getStudentMapping().getLast_name()),
                WordUtils.capitalizeFully(info.getStudentMapping().getFirst_name()),
                info.getStudentMapping().getMiddle_name()==null? "" : WordUtils.capitalizeFully(info.getStudentMapping().getMiddle_name()),
                info.getSection()};
            rowData.add(row);
        }
        PrintResult print = new PrintResult();
        print.setDocumentFormat(doc);
        print.columnNames = colNames;
        print.ROW_DETAILS = rowData;
        print.fileName = "student_list_" + searchWord.toLowerCase();
        print.reportTitleIntro = SystemProperties.instance().getCurrentTermString();
        if(isFiltered)
            previousReportDetail = "Student Search Result For " + searchWord.toUpperCase() + (chkbx_onlyEnrolled.isSelected() && isFiltered? " (Enrolled Student"+(students.size()>1? "s" : "")+")" : "");
        print.reportOtherDetail = previousReportDetail==null? "Student Search Result For " + searchWord.toUpperCase() : previousReportDetail;
        print.reportTitleHeader = "Student Result List";
        print.whenStarted(() -> {
            btn_print.setDisable(true);
            super.cursorWait();
        });
        print.whenCancelled(() -> {
            Notifications.create()
                    .title("Request Cancelled")
                    .text("Sorry for the inconviniece.")
                    .showWarning();
        });
        print.whenFailed(() -> {
            Notifications.create()
                    .title("Request Failed")
                    .text("Something went wrong. Sorry for the inconviniece.")
                    .showInformation();
        });
        print.whenSuccess(() -> {
            btn_print.setDisable(false);
            Notifications.create()
                    .title("Printing Results")
                    .text("Please wait a moment.")
                    .showInformation();
        });
        print.whenFinished(() -> {
            btn_print.setDisable(false);
            super.cursorDefault();
        });
        //----------------------------------------------------------------------
        if(ReportsUtility.savePrintLogs(null, "Student Result List".toUpperCase(), "STUDENTS", "INITIAL"))
                print.transact();
    }

    private String searchWord = "";

    public void onSearch() {
        try {
            getScene().setCursor(Cursor.WAIT);
        } catch (NullPointerException e) {
        }
        isFiltered = true;
        if (vbox_result.isVisible()) {
            searchWord = MonoString.removeExtraSpace(txt_search_key.getText()).toUpperCase();
            txt_search_key_main.setText(txt_search_key.getText());
        } else {
            searchWord = MonoString.removeExtraSpace(txt_search_key_main.getText()).toUpperCase();
            txt_search_key.setText(txt_search_key_main.getText());
        }
        if (searchWord.isEmpty()) {
            lbl_status.setText("Nothing to search. Please enter a " + SELECTED_mode.toLowerCase() + ". You can change the selected category.");
            lbl_status1.setText("Nothing to search. Please enter a " + SELECTED_mode.toLowerCase() + ". You can change the selected category.");
            return;
        } else {
            lbl_status.setText("Searching please wait...");
            lbl_status1.setText("Searching please wait...");
        }

        if (SELECTED_mode.equalsIgnoreCase(NAME)) {
            fetchStudent(searchWord, false, null, null);
        } else if (SELECTED_mode.equalsIgnoreCase(STUDENT_NUM)) {
            fetchStudent(searchWord, false, null, null);
        } else if (SELECTED_mode.equalsIgnoreCase(ACAD_PROG)) {
            String[] keys = searchWord.split(" ");
            if (keys.length == 1) {
                fetchStudent(searchWord, true, null, null);
            } else if (keys.length >= 2) {
                String[] section = keys[1].split("-");
                if (section.length == 1) {
                    // assuming that the inputted text is a program code and a section name
//                    Criterion search_for_academic_program = SQL.or(
//                            Restrictions.ilike(DB.academic_program().code, keys[0], MatchMode.ANYWHERE));
                    fetchStudent(keys[0], true, keys[1], null);
                } else if (section.length == 2) {
//                    // assuming that the inputted text is a program code, a section name and a group
//                    Criterion search_for_academic_program = SQL.or(
//                            Restrictions.ilike(DB.academic_program().code, keys[0], MatchMode.ANYWHERE));
                    Integer group;
                    try {
                        group = Integer.valueOf(section[1].substring(1));
                    } catch (Exception e) {
                        group = null;
                    }
                    fetchStudent(keys[0], true, section[0], group);
                }
            }
        }
    }

    private ArrayList<StudentMapping> students = new ArrayList<>();

    private void fetchStudent(String value, Boolean isSearchingForAcadProgCode, String section, Integer group) {
        students.clear();
        FetchStudents fetch = new FetchStudents();
        fetch.setSearchValue(value);
        fetch.setEnrolledSelected(isEnrolledSelected);
        fetch.isSearchingForAcadProgCode(isSearchingForAcadProgCode);
        fetch.setSectionKey(section);
        fetch.setGroupKey(group);

        //
        fetch.setSearchMode(SELECTED_mode);
        fetch.whenStarted(() -> {
            vbox_no_result.setVisible(false);
            tableStudent.getChildren().clear();
            vbox_list.setVisible(false);
            
            btn_home1.setDisable(true);
        });
        fetch.whenRunning(() -> {
            lbl_status.setText("Loading...");
        });
        fetch.whenSuccess(() -> {
            students = fetch.getResult();
//            vbox_result.setVisible(true);
            if (students.isEmpty()) {
                lbl_status1.setText("No result. " + fetch.getMessage());
//                vbox_no_result.setVisible(true);
                changeResult(vbox_no_result);
            } else {
                createStudentTable(students);
                lbl_status1.setText("Done. Total Result Found: " + students.size());
                if (!vbox_list.isVisible()) {
                    changeResult(vbox_list);
                }
            }
        });
        fetch.whenFailed(() -> {
            lbl_status.setText("Process failed. Something went wrong. " + fetch.getMessage());
            lbl_status1.setText("Process failed. Something went wrong. " + fetch.getMessage());
//            vbox_result.setVisible(true);
            tableStudent.getChildren().clear();
//            vbox_no_result.setVisible(true);
            changeResult(vbox_no_result);
        });
        fetch.whenCancelled(() -> {
            lbl_status.setText(fetch.getMessage());
            lbl_status1.setText(fetch.getMessage());
//            vbox_result.setVisible(true);
            tableStudent.getChildren().clear();
//            vbox_no_result.setVisible(true);
            changeResult(vbox_no_result);
        });
        fetch.whenFinished(() -> {
            if (fetch.getResult() == null) {
//                vbox_no_result.setVisible(true);
                tableStudent.getChildren().clear();
                lbl_status1.setText("No result. " + fetch.getMessage());
                lbl_status1.setText("No result. " + fetch.getMessage());
            }
            if (!vbox_result.isVisible()) {
                changeHome(vbox_result);
            }
            btn_home1.setDisable(false);
            try {
                getScene().setCursor(Cursor.DEFAULT);
            } catch (NullPointerException e) {
            }
        });
        fetch.transact();
    }

    private void changeResult(Node node) {
        Animate.fade(node, 150, () -> {
            vbox_list.setVisible(false);
            vbox_no_result.setVisible(false);

            node.setVisible(true);
        }, vbox_list, vbox_no_result);

    }

    private void changeHome(Node node) {
        Animate.fade(node, 150, () -> {
            vbox_home.setVisible(false);
            vbox_result.setVisible(false);

            node.setVisible(true);
        }, vbox_home, vbox_result);
    }

    private SimpleTable tableStudent = new SimpleTable();

    private void createStudentTable(ArrayList<StudentMapping> studentToDisplay) {
        try {
            tableStudent.getChildren().clear();

            if (studentToDisplay.isEmpty()) {
                System.out.println("NO STUDENT FOUND");
                return;
            }
            for (StudentMapping student : studentToDisplay) {
                createRow(student, tableStudent);
            }

            SimpleTableView simpleTableView = new SimpleTableView();
            simpleTableView.setTable(tableStudent);
            simpleTableView.setFixedWidth(true);
            simpleTableView.setParentOnScene(vbox_list);
        } catch (NullPointerException a) {
            a.printStackTrace();
        }
    }

    private final static String KEY_MORE_INFO = "MORE_INFO";

    private void createRow(StudentMapping student, SimpleTable table) {
        SimpleTableRow row = new SimpleTableRow();
        row.setRowHeight(80.0);
        StudentInformation studInfo = new StudentInformation(student);
        HBox studentRow = (HBox) Mono.fx().create()
                .setPackageName("update5.org.cict.student.layout")
                .setFxmlDocument("student-row")
                .makeFX()
                .pullOutLayout();

        Label lbl_id = searchAccessibilityText(studentRow, "id");
        Label lbl_name = searchAccessibilityText(studentRow, "name");
        Label lbl_section = searchAccessibilityText(studentRow, "section");
        lbl_id.setText(student.getId());
        String middleName = student.getMiddle_name();
        String mName = middleName == null || middleName.isEmpty() ? "" : (" " + middleName);
        lbl_name.setText(student.getLast_name() + ", " + student.getFirst_name() + mName);
        lbl_section.setText(studInfo.getSection());

        SimpleTableCell cellParent = new SimpleTableCell();
        cellParent.setResizePriority(Priority.ALWAYS);
        cellParent.setContent(studentRow);

        row.addCell(cellParent);

        row.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            showStudentInfo((StudentInformation) row.getRowMetaData().get(KEY_MORE_INFO));
        });

        row.getRowMetaData().put(KEY_MORE_INFO, studInfo);
        table.addRow(row);
    }

    private void showStudentInfo(StudentInformation currentStudent) {
        InfoStudentController controller = new InfoStudentController(currentStudent);
        LayoutDataFX home = new LayoutDataFX(application_root, this);
        controller.setHomeFX(home);
        Pane fxRoot = Mono.fx().create()
                .setPackageName("update5.org.cict.student.layout")
                .setFxmlDocument("InfoStudent")
                .makeFX()
                .setController(controller)
                .pullOutLayout();

        Animate.fade(application_root, 150, () -> {
            super.replaceRoot(fxRoot);
        }, fxRoot);
    }

    private void setComboBox() {
        cmb_category_main.getItems().clear();
        cmb_category_main.getItems().add("Student Number");
        cmb_category_main.getItems().add("Name");
        cmb_category_main.getItems().add("Academic Program");
        cmb_category_main.getSelectionModel().selectFirst();

        cmb_category.getItems().clear();
        cmb_category.getItems().add("Student Number");
        cmb_category.getItems().add("Name");
        cmb_category.getItems().add("Academic Program");
        cmb_category.getSelectionModel().selectFirst();
    }

    private class FetchStudents extends Transaction {

        private String searchValue;

        public void setSearchValue(String value) {
            this.searchValue = value;
        }

        private String section_key;

        public void setSectionKey(String str) {
            this.section_key = str;
        }

        private Integer group_key;

        public void setGroupKey(Integer str) {
            this.group_key = str;
        }

        private Boolean isEnrolledSelected = false;

        private void setEnrolledSelected(Boolean res) {
            this.isEnrolledSelected = res;
        }

        private ArrayList<StudentMapping> students = new ArrayList<>();
        private ArrayList<StudentMapping> enrolledStudents = new ArrayList<>();

        public ArrayList<StudentMapping> getResult() {
            return isEnrolledSelected ? enrolledStudents : students;
        }

        public ArrayList<StudentMapping> getEnrolledStudents() {
            return enrolledStudents;
        }

        private String msg = "";

        public String getMessage() {
            return msg;
        }

        private Boolean isSearchingForAcadProgCode = false;

        private void isSearchingForAcadProgCode(Boolean is_it) {
            this.isSearchingForAcadProgCode = is_it;
        }

        private String mode;

        public void setSearchMode(String mode) {
            this.mode = mode;
        }

        @Override
        protected boolean transaction() {
            if (!isSearchingForAcadProgCode) {
                Searcher searchStudent = Mono.orm()
                        .newSearch(Database.connect().student())
                        .pull();

                students = this.recursiveQuery(searchStudent)
                        .execute(Order.asc(DB.student().last_name)).all();

                if (students == null ? true : students.isEmpty()) {
                    msg = "No student found with the given key.";
                    return false;
                }
            } else {
                ArrayList<AcademicProgramMapping> acads = Mono.orm().newSearch(Database.connect().academic_program())
                        .put(SQL.or(Restrictions.ilike(DB.academic_program().code,
                                (searchValue), MatchMode.ANYWHERE))).active().all();
                if (acads == null) {
                    msg = "No academic program found with the given key.";
                    return false;
                }
                for (AcademicProgramMapping acad : acads) {
                    ArrayList<CurriculumMapping> curriculums = Mono.orm().newSearch(Database.connect().curriculum())
                            .eq(DB.curriculum().ACADPROG_id, acad.getId()).active().all();
                    if (curriculums == null) {
                        continue;
                    }
                    for (CurriculumMapping curriculum : curriculums) {
                        ArrayList<StudentMapping> students_temp = Mono.orm().newSearch(Database.connect().student())
                                .eq(DB.student().CURRICULUM_id, curriculum.getId())
                                .active(Order.asc(DB.student().last_name)).all();
                        if (students_temp == null ? true : students_temp.isEmpty()) {
                            continue;
                        }
                        if (section_key != null) {
                            int count = 0;
                            for (StudentMapping student_temp : students_temp) {
                                if (student_temp.getSection() != null && student_temp.getYear_level() != null) {
                                    if (section_key.equalsIgnoreCase(student_temp.getYear_level() + student_temp.getSection())) {
                                        if (group_key != null) {
                                            if (student_temp.get_group() != null) {
                                                if (group_key.equals(student_temp.get_group())) {
                                                    students.add(student_temp);
                                                    count++;
                                                }
                                            }
                                        } else {
                                            students.add(student_temp);
                                            count++;
                                        }
                                    }
                                }
                            }
                        } else {
                            students.addAll(students_temp);
                        }
                    }
                }
            }
            if (!isEnrolledSelected) {
                return true;
            }
            AcademicTermMapping acadTerm = SystemProperties.instance().getCurrentAcademicTerm();
            for (StudentMapping student : students) {
                if (student.getLast_evaluation_term() != null) {
                    if (acadTerm == null) {
                        enrolledStudents.add(student);
                        continue;
                    }
                    if (student.getLast_evaluation_term().equals(acadTerm.getId())) {
                        enrolledStudents.add(student);
                    }
                }
            }
            return true;
        }

        private Searcher recursiveQuery(Searcher searchQuery) {
            if (mode.equalsIgnoreCase("NAME")) {
                for (String textPart : this.searchValue.split(" ")) {
                    if (textPart.isEmpty()) {
                        continue;
                    }
                    searchQuery.put(forAllNames(textPart));
                }
            } else if (mode.equalsIgnoreCase("STUDENT NUMBER")) {
                searchQuery.put(SQL
                        .or(Restrictions.ilike(DB.student().id, (searchValue), MatchMode.ANYWHERE)));
            }
            return searchQuery.pull();
        }

        private Criterion forAllNames(String textPart) {
            return SQL.or(
                    Restrictions.ilike(DB.faculty().first_name, textPart, MatchMode.ANYWHERE),
                    Restrictions.ilike(DB.faculty().middle_name, textPart, MatchMode.ANYWHERE),
                    Restrictions.ilike(DB.faculty().last_name, textPart, MatchMode.ANYWHERE)
            );
        }

        @Override
        protected void after() {

        }

    }
}
