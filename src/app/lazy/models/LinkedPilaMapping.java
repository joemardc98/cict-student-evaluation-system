// SQL_db: cictems
// SQL_table: linked_pila
// Mono Models
// Monosync Framewrok v9.08.16
// Generated using LazyMono
// This code is computer generated, do not modify
// Author: Jhon Melvin Nieto Perello
// Contact: jhmvinperello@gmail.com
//
// The Framework uses Hibernate as its ORM
// For more information about Hibernate visit hibernate.org

package app.lazy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "linked_pila", catalog = "cictems")
public class LinkedPilaMapping implements java.io.Serializable, com.jhmvin.orm.MonoMapping {


private java.lang.Integer id;
private java.lang.Integer ACADTERM_id;
private java.lang.Integer STUDENT_id;
private java.lang.Integer ACCOUNT_id;
private java.lang.Integer SETTINGS_id;
private java.lang.Integer NOTIFIED;
private java.lang.String conforme;
private java.lang.String course;
private java.lang.String imei;
private java.util.Date request_accepted;
private java.util.Date request_called;
private java.util.Date request_validity;
private java.lang.Integer floor_assignment;
private java.lang.Integer floor_number;
private java.lang.String cluster_name;
private java.lang.String status;
private java.lang.String remarks;
private java.lang.Integer active;

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", nullable = false, length = 10)
public java.lang.Integer getId() {
	return this.id;
}

public void setId(java.lang.Integer fieldId) {
	this.id = fieldId;
}

@Column(name = "ACADTERM_id", nullable = true, length = 10)
public java.lang.Integer getACADTERM_id() {
	return this.ACADTERM_id;
}

public void setACADTERM_id(java.lang.Integer fieldAcadtermId) {
	this.ACADTERM_id = fieldAcadtermId;
}

@Column(name = "STUDENT_id", nullable = true, length = 10)
public java.lang.Integer getSTUDENT_id() {
	return this.STUDENT_id;
}

public void setSTUDENT_id(java.lang.Integer fieldStudentId) {
	this.STUDENT_id = fieldStudentId;
}

@Column(name = "ACCOUNT_id", nullable = true, length = 10)
public java.lang.Integer getACCOUNT_id() {
	return this.ACCOUNT_id;
}

public void setACCOUNT_id(java.lang.Integer fieldAccountId) {
	this.ACCOUNT_id = fieldAccountId;
}

@Column(name = "SETTINGS_id", nullable = true, length = 10)
public java.lang.Integer getSETTINGS_id() {
	return this.SETTINGS_id;
}

public void setSETTINGS_id(java.lang.Integer fieldSettingsId) {
	this.SETTINGS_id = fieldSettingsId;
}

@Column(name = "NOTIFIED", nullable = true, length = 10)
public java.lang.Integer getNOTIFIED() {
	return this.NOTIFIED;
}

public void setNOTIFIED(java.lang.Integer fieldNotified) {
	this.NOTIFIED = fieldNotified;
}

@Column(name = "conforme", nullable = true, length = 50)
public java.lang.String getConforme() {
	return this.conforme;
}

public void setConforme(java.lang.String fieldConforme) {
	this.conforme = fieldConforme;
}

@Column(name = "course", nullable = true, length = 50)
public java.lang.String getCourse() {
	return this.course;
}

public void setCourse(java.lang.String fieldCourse) {
	this.course = fieldCourse;
}

@Column(name = "imei", nullable = true, length = 50)
public java.lang.String getImei() {
	return this.imei;
}

public void setImei(java.lang.String fieldImei) {
	this.imei = fieldImei;
}

@Column(name = "request_accepted", nullable = true, length = 19)
@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
public java.util.Date getRequest_accepted() {
	return this.request_accepted;
}

public void setRequest_accepted(java.util.Date fieldRequestAccepted) {
	this.request_accepted = fieldRequestAccepted;
}

@Column(name = "request_called", nullable = true, length = 19)
@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
public java.util.Date getRequest_called() {
	return this.request_called;
}

public void setRequest_called(java.util.Date fieldRequestCalled) {
	this.request_called = fieldRequestCalled;
}

@Column(name = "request_validity", nullable = true, length = 19)
@javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
public java.util.Date getRequest_validity() {
	return this.request_validity;
}

public void setRequest_validity(java.util.Date fieldRequestValidity) {
	this.request_validity = fieldRequestValidity;
}

@Column(name = "floor_assignment", nullable = true, length = 10)
public java.lang.Integer getFloor_assignment() {
	return this.floor_assignment;
}

public void setFloor_assignment(java.lang.Integer fieldFloorAssignment) {
	this.floor_assignment = fieldFloorAssignment;
}

@Column(name = "floor_number", nullable = true, length = 10)
public java.lang.Integer getFloor_number() {
	return this.floor_number;
}

public void setFloor_number(java.lang.Integer fieldFloorNumber) {
	this.floor_number = fieldFloorNumber;
}

@Column(name = "cluster_name", nullable = true, length = 50)
public java.lang.String getCluster_name() {
	return this.cluster_name;
}

public void setCluster_name(java.lang.String fieldClusterName) {
	this.cluster_name = fieldClusterName;
}

@Column(name = "status", nullable = false, length = 50)
public java.lang.String getStatus() {
	return this.status;
}

public void setStatus(java.lang.String fieldStatus) {
	this.status = fieldStatus;
}

@Column(name = "remarks", nullable = false, length = 50)
public java.lang.String getRemarks() {
	return this.remarks;
}

public void setRemarks(java.lang.String fieldRemarks) {
	this.remarks = fieldRemarks;
}

@Column(name = "active", nullable = true, length = 10)
public java.lang.Integer getActive() {
	return this.active;
}

public void setActive(java.lang.Integer fieldActive) {
	this.active = fieldActive;
}

@Override
public LinkedPilaMapping copy() {
LinkedPilaMapping copyMe = new LinkedPilaMapping();
        /**
         * A.I. Field Do Not Copy.
         *
         * copyMe.id = this.id;
         */
copyMe.ACADTERM_id = this.ACADTERM_id;
copyMe.STUDENT_id = this.STUDENT_id;
copyMe.ACCOUNT_id = this.ACCOUNT_id;
copyMe.SETTINGS_id = this.SETTINGS_id;
copyMe.NOTIFIED = this.NOTIFIED;
copyMe.conforme = this.conforme;
copyMe.course = this.course;
copyMe.imei = this.imei;
copyMe.request_accepted = this.request_accepted;
copyMe.request_called = this.request_called;
copyMe.request_validity = this.request_validity;
copyMe.floor_assignment = this.floor_assignment;
copyMe.floor_number = this.floor_number;
copyMe.cluster_name = this.cluster_name;
copyMe.status = this.status;
copyMe.remarks = this.remarks;
copyMe.active = this.active;
return copyMe;
}

}
