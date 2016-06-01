databaseChangeLog = {

	changeSet(author: "gabor (generated)", id: "1450135543115-1") {
		createTable(tableName: "BIO_ASSAY") {
			column(name: "BIO_ASSAY_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAYPK")
			}

			column(name: "assay_platform_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(4000 char)")

			column(name: "EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PROTOCOL", type: "varchar2(400 char)")

			column(name: "REQUESTOR", type: "varchar2(400 char)")

			column(name: "SAMPLE_RECEIVE_DATE", type: "timestamp")

			column(name: "SAMPLE_TYPE", type: "varchar2(400 char)")

			column(name: "STUDY", type: "varchar2(400 char)")

			column(name: "TEST_DATE", type: "timestamp")

			column(name: "BIO_ASSAY_TYPE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-2") {
		createTable(tableName: "BIO_ASSAY_ANALYSIS") {
			column(name: "BIO_ASSAY_ANALYSIS_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_ANAPK")
			}

			column(name: "ANALYSIS_METHOD_CD", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASY_ANALYSIS_PLTFM_ID", type: "number(19,0)")

			column(name: "ANALYST_ID", type: "varchar2(1020 char)")

			column(name: "BIO_ASSAY_DATA_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ANALYSIS_CREATE_DATE", type: "timestamp")

			column(name: "DATA_COUNT", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FOLD_CHANGE_CUTOFF", type: "double precision")

			column(name: "LONG_DESCRIPTION", type: "varchar2(4000 char)")

			column(name: "ANALYSIS_NAME", type: "varchar2(1000 char)")

			column(name: "PVALUE_CUTOFF", type: "double precision")

			column(name: "QA_CRITERIA", type: "varchar2(4000 char)")

			column(name: "RVALUE_CUTOFF", type: "double precision")

			column(name: "SHORT_DESCRIPTION", type: "varchar2(1020 char)")

			column(name: "TEA_DATA_COUNT", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "ANALYSIS_TYPE", type: "varchar2(400 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-3") {
		createTable(tableName: "BIO_ASSAY_ANALYSIS_DATA") {
			column(name: "BIO_ASY_ANALYSIS_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_ANAPK")
			}

			column(name: "ADJUSTED_P_VALUE_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ADJUSTED_PVALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_ANALYSIS_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_PLATFORM_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CUT_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_FEATURE_GROUP_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FEATURE_GROUP_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "FOLD_CHANGE_RATIO", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "NUMERIC_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "NUMERIC_VALUE_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PREFERRED_PVALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "R_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "RAW_PVALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "RESULTS_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "RHO_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "TEA_NORMALIZED_PVALUE", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-4") {
		createTable(tableName: "BIO_ASSAY_ANALYSIS_DATA_TEA") {
			column(name: "BIO_ASY_ANALYSIS_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_ANAPK")
			}

			column(name: "ADJUSTED_P_VALUE_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ADJUSTED_PVALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_ANALYSIS_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_PLATFORM_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CUT_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_EXPERIMENT_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_FEATURE_GROUP_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FEATURE_GROUP_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "FOLD_CHANGE_RATIO", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "NUMERIC_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "NUMERIC_VALUE_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PREFERRED_PVALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "R_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "RAW_PVALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "RESULTS_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "RHO_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "TEA_NORMALIZED_PVALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "TEA_RANK", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-5") {
		createTable(tableName: "BIO_ASSAY_DATA") {
			column(name: "BIO_ASSAY_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_DATPK")
			}

			column(name: "BIO_ASSAY_DATASET_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_SAMPLE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FEATURE_GROUP_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "FLOAT_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "LOG10_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "LOG2_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "NUMERIC_VALUE", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "TEXT_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-6") {
		createTable(tableName: "BIO_ASSAY_DATA_ANNOTATION") {
			column(name: "BIO_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_FEATURE_GROUP_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-7") {
		createTable(tableName: "BIO_ASSAY_DATA_STATS") {
			column(name: "BIO_ASSAY_DATA_STATS_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_DATPK")
			}

			column(name: "BIO_ASSAY_DATASET_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_FEATURE_GROUP_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FEATURE_GROUP_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MAX_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "MEAN_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "MIN_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "QUARTILE_1", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "QUARTILE_2", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "QUARTILE_3", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "BIO_SAMPLE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_SAMPLE_COUNT", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "STD_DEV_VALUE", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "VALUE_NORMALIZE_METHOD", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-8") {
		createTable(tableName: "BIO_ASSAY_DATASET") {
			column(name: "BIO_ASSAY_DATASET_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_DATPK")
			}

			column(name: "BIO_ASSAY_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CREATE_DATE", type: "timestamp")

			column(name: "DATASET_CRITERIA", type: "varchar2(2000 char)")

			column(name: "DATASET_DESCRIPTION", type: "varchar2(2000 char)")

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DATASET_NAME", type: "varchar2(800 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-9") {
		createTable(tableName: "BIO_ASSAY_FEATURE_GROUP") {
			column(name: "BIO_ASSAY_FEATURE_GROUP_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_FEAPK")
			}

			column(name: "FEATURE_GROUP_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "FEATURE_GROUP_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-10") {
		createTable(tableName: "BIO_ASSAY_PLATFORM") {
			column(name: "BIO_ASSAY_PLATFORM_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_PLAPK")
			}

			column(name: "PLATFORM_ACCESSION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PLATFORM_ARRAY", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PLATFORM_DESCRIPTION", type: "varchar2(2000 char)")

			column(name: "PLATFORM_NAME", type: "varchar2(400 char)")

			column(name: "PLATFORM_ORGANISM", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PLATFORM_VERSION", type: "varchar2(400 char)")

			column(name: "PLATFORM_VENDOR", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-11") {
		createTable(tableName: "BIO_ASSAY_SAMPLE") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASSAY_SAMPK")
			}

			column(name: "BIO_ASSAY_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_CLINIC_TRIAL_TIMEPOINT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_SAMPLE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-12") {
		createTable(tableName: "bio_asy_analysis_dataset") {
			column(name: "BIO_ASSAY_ANALYSIS_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "bio_assay_dataset_id", type: "number(19,0)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-13") {
		createTable(tableName: "BIO_ASY_ANALYSIS_PLTFM") {
			column(name: "BIO_ASY_ANALYSIS_PLTFM_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_ASY_ANALYPK")
			}

			column(name: "PLATFORM_DESCRIPTION", type: "varchar2(2000 char)")

			column(name: "PLATFORM_NAME", type: "varchar2(400 char)")

			column(name: "PLATFORM_VERSION", type: "varchar2(400 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-14") {
		createTable(tableName: "BIO_CELL_LINE") {
			column(name: "BIO_CELL_LINE_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CELL_LINEPK")
			}

			column(name: "ATTC_NUMBER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_DISEASE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CELL_LINE_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_STAGE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_SUBTYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "METASTATIC_SITE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ORIGIN", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PRIMARY_SITE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SPECIES", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-15") {
		createTable(tableName: "BIO_CGDCP_DATA") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CGDCP_DATPK")
			}

			column(name: "CELL_LINE_ID", type: "number(19,0)")

			column(name: "EVIDENCE_CODE", type: "varchar2(400 char)")

			column(name: "NCI_DISEASE_CONCEPT_CODE", type: "varchar2(400 char)")

			column(name: "NCI_DRUG_CONCEPT_CODE", type: "varchar2(400 char)")

			column(name: "NCI_ROLE_CODE", type: "varchar2(400 char)")

			column(name: "NEGATION_INDICATOR", type: "varchar2(1 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-16") {
		createTable(tableName: "BIO_CLINICAL_TRIAL") {
			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CLINICAL_PK")
			}

			column(name: "BLINDING_PROCEDURE", type: "varchar2(255 char)")

			column(name: "DOSING_REGIMEN", type: "varchar2(255 char)")

			column(name: "DURATION_OF_STUDY_WEEKS", type: "number(19,0)")

			column(name: "EXCLUSION_CRITERIA", type: "long")

			column(name: "GENDER_RESTRICTION_MFB", type: "varchar2(255 char)")

			column(name: "GROUP_ASSIGNMENT", type: "varchar2(255 char)")

			column(name: "INCLUSION_CRITERIA", type: "long")

			column(name: "MAX_AGE", type: "number(19,0)")

			column(name: "MIN_AGE", type: "number(19,0)")

			column(name: "NUMBER_OF_PATIENTS", type: "number(19,0)")

			column(name: "NUMBER_OF_SITES", type: "number(19,0)")

			column(name: "PRIMARY_END_POINTS", type: "varchar2(255 char)")

			column(name: "ROUTE_OF_ADMINISTRATION", type: "varchar2(255 char)")

			column(name: "SECONDARY_END_POINTS", type: "varchar2(255 char)")

			column(name: "SECONDARY_IDS", type: "varchar2(255 char)")

			column(name: "STUDY_OWNER", type: "varchar2(255 char)")

			column(name: "STUDY_PHASE", type: "varchar2(255 char)")

			column(name: "STUDYTYPE", type: "varchar2(255 char)")

			column(name: "SUBJECTS", type: "varchar2(255 char)")

			column(name: "TRIAL_NUMBER", type: "varchar2(255 char)")

			column(name: "TYPE_OF_CONTROL", type: "varchar2(255 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-17") {
		createTable(tableName: "BIO_CLINICAL_TRIAL_PT_GROUP") {
			column(name: "BIO_CLINICAL_TRIAL_P_GROUP_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CLINICAL_PK")
			}

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(2000 char)")

			column(name: "NAME", type: "varchar2(1020 char)")

			column(name: "NUMBER_OF_PATIENTS", type: "number(19,0)")

			column(name: "PATIENT_GROUP_TYPE_CODE", type: "varchar2(400 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-18") {
		createTable(tableName: "BIO_CLINICAL_TRIAL_TIME_POINT") {
			column(name: "BIO_CLINIC_TRIAL_TIMEPOINT_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CLINICAL_PK")
			}

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "END_DATE", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "START_DATE", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "TIME_POINT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TIME_POINT_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-19") {
		createTable(tableName: "BIO_COMPOUND") {
			column(name: "BIO_COMPOUND_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_COMPOUNDPK")
			}

			column(name: "BRAND_NAME", type: "varchar2(400 char)")

			column(name: "CAS_REGISTRY", type: "varchar2(800 char)")

			column(name: "CHEMICAL_NAME", type: "varchar2(800 char)")

			column(name: "CNTO_NUMBER", type: "varchar2(400 char)")

			column(name: "CODE_NAME", type: "varchar2(400 char)")

			column(name: "DESCRIPTION", type: "varchar2(2000 char)")

			column(name: "GENERIC_NAME", type: "varchar2(400 char)")

			column(name: "MECHANISM", type: "varchar2(800 char)")

			column(name: "JNJ_NUMBER", type: "varchar2(400 char)")

			column(name: "PRODUCT_CATEGORY", type: "varchar2(400 char)")

			column(name: "SOURCE_CD", type: "varchar2(100 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-20") {
		createTable(tableName: "BIO_CONCEPT_CODE") {
			column(name: "BIO_CONCEPT_CODE_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CONCEPT_CPK")
			}

			column(name: "BIO_CONCEPT_CODE", type: "varchar2(400 char)")

			column(name: "CODE_DESCRIPTION", type: "varchar2(2000 char)")

			column(name: "CODE_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CODE_TYPE_NAME", type: "varchar2(400 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-21") {
		createTable(tableName: "BIO_CONTENT") {
			column(name: "BIO_FILE_CONTENT_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CONTENTPK")
			}

			column(name: "ABSTRACT", type: "varchar2(4000 char)")

			column(name: "LOCATION", type: "varchar2(800 char)")

			column(name: "FILE_NAME", type: "varchar2(2000 char)")

			column(name: "REPOSITORY_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "TITLE", type: "varchar2(2000 char)")

			column(name: "FILE_TYPE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-22") {
		createTable(tableName: "BIO_CONTENT_REFERENCE") {
			column(name: "BIO_CONTENT_REFERENCE_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CONTENT_RPK")
			}

			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_CONTENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CONTENT_REFERENCE_TYPE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-23") {
		createTable(tableName: "BIO_CONTENT_REPOSITORY") {
			column(name: "BIO_CONTENT_REPO_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CONTENT_RPK")
			}

			column(name: "ACTIVE_Y_N", type: "varchar2(1 char)")

			column(name: "LOCATION", type: "varchar2(1020 char)")

			column(name: "LOCATION_TYPE", type: "varchar2(400 char)")

			column(name: "REPOSITORY_TYPE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-24") {
		createTable(tableName: "BIO_CURATION_DATASET") {
			column(name: "BIO_CURATION_DATASET_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_CURATION_PK")
			}

			column(name: "BIO_ASY_ANALYSIS_PLATFORM_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CREATE_DATE", type: "timestamp")

			column(name: "CREATOR", type: "number(19,0)")

			column(name: "BIO_CURATION_TYPE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-25") {
		createTable(tableName: "BIO_DATA_ATTRIBUTE") {
			column(name: "BIO_DATA_ATTRIBUTE_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_DATA_ATTRPK")
			}

			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PROPERTY_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PROPERTY_UNIT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PROPERTY_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-26") {
		createTable(tableName: "bio_data_compound") {
			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_COMPOUND_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-27") {
		createTable(tableName: "BIO_DATA_CORREL_DESCR") {
			column(name: "BIO_DATA_CORREL_DESCR_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_DATA_CORRPK")
			}

			column(name: "CORRELATION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SOURCE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SOURCE_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TYPE_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-28") {
		createTable(tableName: "BIO_DATA_CORRELATION") {
			column(name: "BIO_DATA_CORREL_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_DATA_CORRPK")
			}

			column(name: "ASSO_BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_DATA_CORREL_DESCR_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-29") {
		createTable(tableName: "bio_data_disease") {
			column(name: "BIO_DISEASE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-30") {
		createTable(tableName: "BIO_DATA_EXT_CODE") {
			column(name: "BIO_DATA_EXT_CODE_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_DATA_EXT_PK")
			}

			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_DATA_TYPE", type: "varchar2(100 char)")

			column(name: "CODE", type: "varchar2(500 char)") {
				constraints(nullable: "false")
			}

			column(name: "CODE_SOURCE", type: "varchar2(400 char)")

			column(name: "CODE_TYPE", type: "varchar2(400 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-31") {
		createTable(tableName: "BIO_DATA_LITERATURE") {
			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_DATA_LITEPK")
			}

			column(name: "BIO_CURATION_DATASET_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "DATA_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_LIT_REF_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "STATEMENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STATEMENT_STATUS", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-32") {
		createTable(tableName: "bio_data_omic_marker") {
			column(name: "BIO_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "bio_assay_data_statistics_id", type: "number(19,0)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-33") {
		createTable(tableName: "bio_data_taxonomy") {
			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_TAXONOMY_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-34") {
		createTable(tableName: "BIO_DATA_UID") {
			column(name: "BIO_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_DATA_UIDPK")
			}

			column(name: "BIO_DATA_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "UNIQUE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-35") {
		createTable(tableName: "BIO_DISEASE") {
			column(name: "BIO_DISEASE_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_DISEASEPK")
			}

			column(name: "CCS_CATEGORY", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ICD10_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ICD9_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MESH_CODE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PREFERED_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-36") {
		createTable(tableName: "BIO_EXPERIMENT") {
			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_EXPERIMENPK")
			}

			column(name: "ACCESS_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ACCESSION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "BIOMARKER_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "COMPLETION_DATE", type: "timestamp")

			column(name: "COUNTRY", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(4000 char)")

			column(name: "DESIGN", type: "varchar2(4000 char)")

			column(name: "INSTITUTION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "OVERALL_DESIGN", type: "varchar2(4000 char)")

			column(name: "PRIMARY_INVESTIGATOR", type: "varchar2(800 char)")

			column(name: "START_DATE", type: "timestamp")

			column(name: "status", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TITLE", type: "varchar2(2000 char)")

			column(name: "BIO_EXPERIMENT_TYPE", type: "varchar2(400 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-37") {
		createTable(tableName: "BIO_LIT_ALT_DATA") {
			column(name: "BIO_LIT_ALT_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_ALT_DPK")
			}

			column(name: "ALTERATION_TYPE", type: "varchar2(255 char)")

			column(name: "CLIN_ASM_MARKER_TYPE", type: "varchar2(255 char)")

			column(name: "CLIN_ASM_UNIT", type: "varchar2(255 char)")

			column(name: "CLIN_ASM_VALUE", type: "varchar2(255 char)")

			column(name: "CLIN_ATOPY", type: "varchar2(255 char)")

			column(name: "CLIN_BASELINE_PERCENT", type: "varchar2(255 char)")

			column(name: "CLIN_BASELINE_VALUE", type: "varchar2(255 char)")

			column(name: "CLIN_BASELINE_VARIABLE", type: "varchar2(255 char)")

			column(name: "CLIN_CELLULAR_COUNT", type: "varchar2(255 char)")

			column(name: "CLIN_CELLULAR_SOURCE", type: "varchar2(255 char)")

			column(name: "CLIN_CELLULAR_TYPE", type: "varchar2(255 char)")

			column(name: "CLIN_PRIOR_MED_DOSE", type: "varchar2(255 char)")

			column(name: "CLIN_PRIOR_MED_NAME", type: "varchar2(255 char)")

			column(name: "CLIN_PRIOR_MED_PERCENT", type: "varchar2(255 char)")

			column(name: "CLIN_SMOKER", type: "varchar2(255 char)")

			column(name: "CLIN_SUBMUCOSA_MARKER_TYPE", type: "varchar2(255 char)")

			column(name: "CLIN_SUBMUCOSA_UNIT", type: "varchar2(255 char)")

			column(name: "CLIN_SUBMUCOSA_VALUE", type: "varchar2(255 char)")

			column(name: "CONTROL", type: "varchar2(255 char)")

			column(name: "CONTROL_EXP_NUMBER", type: "varchar2(255 char)")

			column(name: "CONTROL_EXP_PERCENT", type: "varchar2(255 char)")

			column(name: "CONTROL_EXP_SD", type: "varchar2(255 char)")

			column(name: "CONTROL_EXP_UNIT", type: "varchar2(255 char)")

			column(name: "CONTROL_EXP_VALUE", type: "varchar2(255 char)")

			column(name: "DESCRIPTION", type: "varchar2(255 char)")

			column(name: "EFFECT", type: "varchar2(255 char)")

			column(name: "EPIGENETIC_REGION", type: "varchar2(255 char)")

			column(name: "EPIGENETIC_TYPE", type: "varchar2(255 char)")

			column(name: "ETL_ID", type: "varchar2(255 char)")

			column(name: "GLC_CONTROL_PERCENT", type: "varchar2(255 char)")

			column(name: "GLC_MOLECULAR_CHANGE", type: "varchar2(255 char)")

			column(name: "GLC_NUMBER", type: "varchar2(255 char)")

			column(name: "GLC_PERCENT", type: "varchar2(255 char)")

			column(name: "GLC_TYPE", type: "varchar2(255 char)")

			column(name: "IN_VITRO_MODEL_ID", type: "number(19,0)")

			column(name: "IN_VIVO_MODEL_ID", type: "number(19,0)")

			column(name: "LOH_LOCI", type: "varchar2(255 char)")

			column(name: "LOSS_EXP_NUMBER", type: "varchar2(255 char)")

			column(name: "LOSS_EXP_PERCENT", type: "varchar2(255 char)")

			column(name: "LOSS_EXP_SD", type: "varchar2(255 char)")

			column(name: "LOSS_EXP_UNIT", type: "varchar2(255 char)")

			column(name: "LOSS_EXP_VALUE", type: "varchar2(255 char)")

			column(name: "MUTATION_CHANGE", type: "varchar2(255 char)")

			column(name: "MUTATION_SITES", type: "varchar2(255 char)")

			column(name: "MUTATION_TYPE", type: "varchar2(255 char)")

			column(name: "OVER_EXP_NUMBER", type: "varchar2(255 char)")

			column(name: "OVER_EXP_PERCENT", type: "varchar2(255 char)")

			column(name: "OVER_EXP_SD", type: "varchar2(255 char)")

			column(name: "OVER_EXP_UNIT", type: "varchar2(255 char)")

			column(name: "OVER_EXP_VALUE", type: "varchar2(255 char)")

			column(name: "PATIENTS_NUMBER", type: "varchar2(255 char)")

			column(name: "PATIENTS_PERCENT", type: "varchar2(255 char)")

			column(name: "POP_BODY_SUBSTANCE", type: "varchar2(255 char)")

			column(name: "POP_CELL_TYPE", type: "varchar2(255 char)")

			column(name: "POP_DESCRIPTION", type: "varchar2(255 char)")

			column(name: "POP_EXCLUSION_CRITERIA", type: "varchar2(255 char)")

			column(name: "POP_EXPERIMENTAL_MODEL", type: "varchar2(255 char)")

			column(name: "POP_INCLUSION_CRITERIA", type: "varchar2(255 char)")

			column(name: "POP_LOCALIZATION", type: "varchar2(255 char)")

			column(name: "POP_NUMBER", type: "varchar2(255 char)")

			column(name: "POP_PHASE", type: "varchar2(255 char)")

			column(name: "POP_STATUS", type: "varchar2(255 char)")

			column(name: "POP_TISSUE", type: "varchar2(255 char)")

			column(name: "POP_TYPE", type: "varchar2(255 char)")

			column(name: "POP_VALUE", type: "varchar2(255 char)")

			column(name: "PTM_CHANGE", type: "varchar2(255 char)")

			column(name: "PTM_REGION", type: "varchar2(255 char)")

			column(name: "PTM_TYPE", type: "varchar2(255 char)")

			column(name: "TECHNIQUES", type: "varchar2(255 char)")

			column(name: "TOTAL_EXP_NUMBER", type: "varchar2(255 char)")

			column(name: "TOTAL_EXP_PERCENT", type: "varchar2(255 char)")

			column(name: "TOTAL_EXP_SD", type: "varchar2(255 char)")

			column(name: "TOTAL_EXP_UNIT", type: "varchar2(255 char)")

			column(name: "TOTAL_EXP_VALUE", type: "varchar2(255 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-38") {
		createTable(tableName: "BIO_LIT_AMD_DATA") {
			column(name: "BIO_LIT_AMD_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_AMD_DPK")
			}

			column(name: "BIO_LIT_ALT_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CO_EXP_NUMBER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CO_EXP_PERCENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CO_EXP_SD", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CO_EXP_UNIT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CO_EXP_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ETL_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MOLECULE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MOLECULE_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MUTATION_CHANGE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MUTATION_NUMBER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MUTATION_PERCENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MUTATION_SITES", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MUTATION_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "OVER_EXP_NUMBER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "OVER_EXP_PERCENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "OVER_EXP_SD", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "OVER_EXP_UNIT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "OVER_EXP_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_EXP_NUMBER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_EXP_PERCENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_EXP_SD", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_EXP_UNIT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_EXP_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_OVER_EXP_NUMBER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_OVER_EXP_PERCENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_OVER_EXP_SD", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_OVER_EXP_UNIT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET_OVER_EXP_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TECHNIQUES", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_EXP_NUMBER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_EXP_PERCENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_EXP_SD", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_EXP_UNIT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_EXP_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-39") {
		createTable(tableName: "BIO_LIT_INH_DATA") {
			column(name: "BIO_LIT_INH_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_INH_DPK")
			}

			column(name: "ADMINISTRATION", type: "varchar2(255 char)")

			column(name: "CASID", type: "varchar2(255 char)")

			column(name: "CONCENTRATION", type: "varchar2(255 char)")

			column(name: "DESCRIPTION", type: "varchar2(255 char)")

			column(name: "EFFECT_ADVERSE", type: "varchar2(255 char)")

			column(name: "EFFECT_BENEFICIAL", type: "varchar2(255 char)")

			column(name: "EFFECT_DESCRIPTION", type: "varchar2(255 char)")

			column(name: "EFFECT_DOWNSTREAM", type: "varchar2(255 char)")

			column(name: "EFFECT_MOLECULAR", type: "varchar2(255 char)")

			column(name: "EFFECT_NUMBER", type: "varchar2(255 char)")

			column(name: "EFFECT_PERCENT", type: "varchar2(255 char)")

			column(name: "EFFECT_PHARMACOS", type: "varchar2(255 char)")

			column(name: "EFFECT_POTENTIALS", type: "varchar2(255 char)")

			column(name: "EFFECT_RESPONSE_RATE", type: "varchar2(255 char)")

			column(name: "EFFECT_SD", type: "varchar2(255 char)")

			column(name: "EFFECT_UNIT", type: "varchar2(255 char)")

			column(name: "EFFECT_VALUE", type: "varchar2(255 char)")

			column(name: "ETL_ID", type: "varchar2(255 char)")

			column(name: "INHIBITOR", type: "varchar2(255 char)")

			column(name: "INHIBITOR_STANDARD_NAME", type: "varchar2(255 char)")

			column(name: "TECHNIQUES", type: "varchar2(255 char)")

			column(name: "TIME_EXPOSURE", type: "varchar2(255 char)")

			column(name: "TREATMENT", type: "varchar2(255 char)")

			column(name: "TRIAL_BODY_SUBSTANCE", type: "varchar2(255 char)")

			column(name: "TRIAL_CELL_LINE", type: "varchar2(255 char)")

			column(name: "TRIAL_CELL_TYPE", type: "varchar2(255 char)")

			column(name: "TRIAL_DESCRIPTION", type: "varchar2(255 char)")

			column(name: "TRIAL_DESIGNS", type: "varchar2(255 char)")

			column(name: "TRIAL_EXPERIMENTAL_MODEL", type: "varchar2(255 char)")

			column(name: "TRIAL_INCLUSION_CRITERIA", type: "varchar2(255 char)")

			column(name: "TRIAL_PATIENTS_NUMBER", type: "varchar2(255 char)")

			column(name: "TRIAL_PHASE", type: "varchar2(255 char)")

			column(name: "TRIAL_STATUS", type: "varchar2(255 char)")

			column(name: "TRIAL_TISSUE", type: "varchar2(255 char)")

			column(name: "TRIAL_TYPE", type: "varchar2(255 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-40") {
		createTable(tableName: "BIO_LIT_INT_DATA") {
			column(name: "BIO_LIT_INT_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_INT_DPK")
			}

			column(name: "EFFECT", type: "varchar2(255 char)")

			column(name: "ETL_ID", type: "varchar2(255 char)")

			column(name: "IN_VITRO_MODEL_ID", type: "number(19,0)")

			column(name: "IN_VIVO_MODEL_ID", type: "number(19,0)")

			column(name: "INTERACTION_MODE", type: "varchar2(255 char)")

			column(name: "LOCALIZATION", type: "varchar2(255 char)")

			column(name: "MECHANISM", type: "varchar2(255 char)")

			column(name: "REGION", type: "varchar2(255 char)")

			column(name: "REGULATION", type: "varchar2(255 char)")

			column(name: "SOURCE_COMPONENT", type: "varchar2(255 char)")

			column(name: "SOURCE_GENE_ID", type: "varchar2(255 char)")

			column(name: "TARGET_COMPONENT", type: "varchar2(255 char)")

			column(name: "TARGET_GENE_ID", type: "varchar2(255 char)")

			column(name: "TECHNIQUES", type: "varchar2(255 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-41") {
		createTable(tableName: "BIO_LIT_INT_MODEL_MV") {
			column(name: "BIO_LIT_INT_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_INT_MPK")
			}

			column(name: "EXPERIMENTAL_MODEL", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-42") {
		createTable(tableName: "BIO_LIT_MODEL_DATA") {
			column(name: "BIO_LIT_MODEL_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_MODELPK")
			}

			column(name: "ANIMAL_WILD_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "BODY_SUBSTANCE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CELL_LINE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CELL_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CHALLENGE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "COMPONENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CONTROL_CHALLENGE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ETL_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "EXPERIMENTAL_MODEL", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "GENE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MODEL_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SENTIZATION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STIMULATION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TISSUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ZYGOSITY", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-43") {
		createTable(tableName: "BIO_LIT_PE_DATA") {
			column(name: "BIO_LIT_PE_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_PE_DAPK")
			}

			column(name: "DESCRIPTION", type: "varchar2(255 char)")

			column(name: "ETL_ID", type: "varchar2(255 char)")

			column(name: "IN_VITRO_MODEL_ID", type: "number(19,0)")

			column(name: "IN_VIVO_MODEL_ID", type: "number(19,0)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-44") {
		createTable(tableName: "BIO_LIT_REF_DATA") {
			column(name: "BIO_LIT_REF_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_REF_DPK")
			}

			column(name: "BACK_REFERENCES", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "COMPONENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "COMPONENT_CLASS", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_DESCRIPTION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_GRADE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_ICD10", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_MESH", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_SITE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_STAGE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_TYPES", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ETL_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "GENE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "MOLECULE_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PHYSIOLOGY", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "REFERENCE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "REFERENCE_TITLE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "REFERENCE_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STAT_CLINICAL", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STAT_CLINICAL_CORRELATION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STAT_COEFFICIENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STAT_DESCRIPTION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STAT_P_VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STAT_TESTS", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "STUDY_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "VARIANT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-45") {
		createTable(tableName: "BIO_LIT_SUM_DATA") {
			column(name: "BIO_LIT_SUM_DATA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_LIT_SUM_DPK")
			}

			column(name: "ALTERATION_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DATA_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DISEASE_SITE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ETL_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SUMMARY", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TARGET", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_AFFECTED_CASES", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_FREQUENCY", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "VARIANT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-46") {
		createTable(tableName: "BIO_MARKER") {
			column(name: "BIO_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_MARKERPK")
			}

			column(name: "BIO_MARKER_TYPE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_MARKER_DESCRIPTION", type: "varchar2(2000 char)")

			column(name: "BIO_MARKER_NAME", type: "varchar2(400 char)")

			column(name: "ORGANISM", type: "varchar2(400 char)")

			column(name: "PRIMARY_EXTERNAL_ID", type: "varchar2(400 char)")

			column(name: "PRIMARY_SOURCE_CODE", type: "varchar2(400 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-47") {
		createTable(tableName: "BIO_MARKER_CORREL_MV") {
			column(name: "MV_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_MARKER_COPK")
			}

			column(name: "ASSO_BIO_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CORREL_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-48") {
		createTable(tableName: "BIO_MARKER_EXP_ANALYSIS_MV") {
			column(name: "MV_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_MARKER_EXPK")
			}

			column(name: "BIO_ASSAY_ANALYSIS_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-49") {
		createTable(tableName: "BIO_PATIENT") {
			column(name: "BIO_PATIENT_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_PATIENTPK")
			}

			column(name: "ADDRESS_ZIP_CODE", type: "varchar2(400 char)")

			column(name: "BIO_CLINICAL_TRIAL_P_GROUP_ID", type: "number(19,0)")

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)")

			column(name: "BIRTH_DATE", type: "timestamp")

			column(name: "BIRTH_DATE_ORIG", type: "varchar2(400 char)")

			column(name: "COUNTRY_CODE", type: "varchar2(400 char)")

			column(name: "ETHNIC_GROUP_CODE", type: "varchar2(400 char)")

			column(name: "FIRST_NAME", type: "varchar2(400 char)")

			column(name: "GENDER_CODE", type: "varchar2(400 char)")

			column(name: "INFORMED_CONSENT_CODE", type: "varchar2(400 char)")

			column(name: "LAST_NAME", type: "varchar2(400 char)")

			column(name: "MIDDLE_NAME", type: "varchar2(400 char)")

			column(name: "RACE_CODE", type: "varchar2(400 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-50") {
		createTable(tableName: "BIO_PATIENT_EVENT") {
			column(name: "BIO_PATIENT_EVENT_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_PATIENT_EPK")
			}

			column(name: "BIO_CLINIC_TRIAL_TIMEPOINT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_PATIENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "EVENT_CODE", type: "varchar2(400 char)")

			column(name: "EVENT_DATE", type: "timestamp")

			column(name: "EVENT_TYPE_CODE", type: "varchar2(400 char)")

			column(name: "SITE", type: "varchar2(800 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-51") {
		createTable(tableName: "BIO_PATIENT_EVENT_ATTRIBUTE") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_PATIENT_EPK")
			}

			column(name: "ATTRIBUTE_NUMERIC_VALUE", type: "varchar2(400 char)")

			column(name: "ATTRIBUTE_TEXT_VALUE", type: "varchar2(400 char)")

			column(name: "BIO_CLINIC_TRIAL_ATTRIBUTE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_PATIENT_ATTR_CODE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_PATIENT_ATTRIBUTE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_PATIENT_EVENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-52") {
		createTable(tableName: "BIO_SAMPLE") {
			column(name: "BIO_SAMPLE_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_SAMPLEPK")
			}

			column(name: "BIO_BANK_ID", type: "number(19,0)")

			column(name: "BIO_PATIENT_EVENT_ID", type: "number(19,0)")

			column(name: "BIO_SUBJECT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_CELL_LINE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CHARACTERISTICS", type: "varchar2(2000 char)")

			column(name: "EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_SAMPLE_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SOURCE", type: "varchar2(400 char)")

			column(name: "SOURCE_CODE", type: "varchar2(400 char)")

			column(name: "BIO_SAMPLE_TYPE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-53") {
		createTable(tableName: "BIO_STATS_EXP_MARKER") {
			column(name: "BIO_STATS_EXP_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_STATS_EXPPK")
			}

			column(name: "BIO_EXPERIMENT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-54") {
		createTable(tableName: "BIO_SUBJECT") {
			column(name: "BIO_SUBJECT_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_SUBJECTPK")
			}

			column(name: "ORGANISM", type: "varchar2(400 char)")

			column(name: "SITE_SUBJECT_ID", type: "number(19,0)")

			column(name: "SOURCE", type: "varchar2(400 char)")

			column(name: "SOURCE_CODE", type: "varchar2(400 char)")

			column(name: "STATUS", type: "varchar2(400 char)")

			column(name: "BIO_SUBJECT_TYPE", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-55") {
		createTable(tableName: "BIO_TAXONOMY") {
			column(name: "BIO_TAXONOMY_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIO_TAXONOMYPK")
			}

			column(name: "TAXON_LABEL", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TAXON_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "NCBI_TAX_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-56") {
		createTable(tableName: "BIOMART.BIOBANK_SAMPLE") {
			column(name: "SAMPLE_TUBE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "BIOMART.BIOBAPK")
			}

			column(name: "ACCESSION_NUMBER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CLIENT_SAMPLE_TUBE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CONTAINER_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "IMPORT_DATE", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "SOURCE_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-57") {
		createTable(tableName: "DE_SAVED_COMPARISON") {
			column(name: "COMPARISON_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "DE_SAVED_COMPPK")
			}

			column(name: "QUERY_ID1", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "QUERY_ID2", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-58") {
		createTable(tableName: "DE_SNP_PROBE_SORTED_DEF") {
			column(name: "SNP_PROBE_SORTED_DEF_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "DE_SNP_PROBE_PK")
			}

			column(name: "CHROM", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "NUM_PROBE", type: "number(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "PLATFORM_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PROBE_DEF", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "snp_id_def", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-59") {
		createTable(tableName: "de_subject_sample_mapping") {
			column(name: "sample_id", type: "varchar2(255 char)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "de_subject_saPK")
			}

			column(name: "assay_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "concept_code", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "patient_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "gpl_id", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "sample_cd", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "trial_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-60") {
		createTable(tableName: "DE_SUBJECT_SNP_DATASET") {
			column(name: "SUBJECT_SNP_DATASET_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "DE_SUBJECT_SNPK")
			}

			column(name: "CONCEPT_CD", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DATASET_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PAIRED_DATASET_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PATIENT_GENDER", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PATIENT_NUM", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PLATFORM_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SAMPLE_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SUBJECT_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TIMEPOINT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TRIAL_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-61") {
		createTable(tableName: "DEAPP.SOLR_SAVE_QUERY") {
			column(name: "SOLR_QUERY_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "DEAPP.SOLR_SAPK")
			}

			column(name: "QUERY_CONTENTS", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "QUERY_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-62") {
		createTable(tableName: "I2B2") {
			column(name: "C_FULLNAME", type: "varchar2(255 char)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "I2B2PK")
			}

			column(name: "C_BASECODE", type: "varchar2(450 char)")

			column(name: "C_COLUMNDATATYPE", type: "varchar2(50 char)")

			column(name: "C_COLUMNNAME", type: "varchar2(50 char)")

			column(name: "C_COMMENT", type: "varchar2(4000 char)")

			column(name: "C_DIMCODE", type: "varchar2(900 char)")

			column(name: "DOWNLOAD_DATE", type: "timestamp")

			column(name: "C_FACTTABLECOLUMN", type: "varchar2(50 char)")

			column(name: "C_HLEVEL", type: "number(19,0)")

			column(name: "IMPORT_DATE", type: "timestamp")

			column(name: "C_METADATAXML", type: "varchar2(4000 char)")

			column(name: "C_NAME", type: "varchar2(2000 char)")

			column(name: "C_OPERATOR", type: "varchar2(10 char)")

			column(name: "SOURCESYSTEM_CD", type: "varchar2(50 char)")

			column(name: "C_SYNONYM_CD", type: "varchar2(1 char)")

			column(name: "C_TABLENAME", type: "varchar2(50 char)")

			column(name: "C_TOOLTIP", type: "varchar2(900 char)")

			column(name: "C_TOTALNUM", type: "number(19,0)")

			column(name: "UPDATE_DATE", type: "timestamp")

			column(name: "VALUETYPE_CD", type: "varchar2(50 char)")

			column(name: "C_VISUALATTRIBUTES", type: "varchar2(3 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-63") {
		createTable(tableName: "I2B2_TAGS") {
			column(name: "TAG_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "I2B2_TAGSPK")
			}

			column(name: "PATH", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TAG", type: "varchar2(400 char)")

			column(name: "TAG_TYPE", type: "varchar2(400 char)")

			column(name: "tags_idx", type: "number(10,0)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-64") {
		createTable(tableName: "I2B2DEMODATA.ASYNC_JOB") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "I2B2DEMODATA.PK")
			}

			column(name: "ALT_VIEWER_URL", type: "varchar2(255 char)")

			column(name: "JOB_NAME", type: "varchar2(255 char)")

			column(name: "JOB_STATUS", type: "varchar2(255 char)")

			column(name: "LAST_RUN_ON", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "JOB_RESULTS", type: "varchar2(255 char)")

			column(name: "RUN_TIME", type: "varchar2(255 char)")

			column(name: "VIEWER_URL", type: "varchar2(255 char)")

			column(name: "JOB_TYPE", type: "varchar2(255 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-65") {
		createTable(tableName: "i2b2DemoData.news_updates") {
			column(name: "newsid", type: "number(10,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "i2b2DemoData.PK")
			}

			column(name: "commentField", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "dataSetName", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "operation", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "ranByUser", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "rowsAffected", type: "number(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "updateDate", type: "timestamp") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-66") {
		createTable(tableName: "NODE_METADATA") {
			column(name: "NODE_METADATA_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "NODE_METADATAPK")
			}

			column(name: "CONCEPT_PATH", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "VALUE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PARENT_CONCEPT_PATH", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-67") {
		createTable(tableName: "PASSWORD_DICTIONARY") {
			column(name: "term", type: "varchar2(255 char)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "PASSWORD_DICTPK")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-68") {
		createTable(tableName: "PATIENT_TRIAL") {
			column(name: "PATIENT_NUM", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "PATIENT_TRIALPK")
			}

			column(name: "SECURE_OBJ_TOKEN", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "TRIAL", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-69") {
		createTable(tableName: "QT_PATIENT_SAMPLE_COLLECTION") {
			column(name: "SAMPLE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "QT_PATIENT_SAPK")
			}

			column(name: "PATIENT_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "RESULT_INSTANCE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-70") {
		createTable(tableName: "SEARCH_APP_ACCESS_LOG") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_APP_ACPK")
			}

			column(name: "ACCESS_TIME", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "EVENT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "EVENT_MESSAGE", type: "varchar2(255 char)")

			column(name: "REQUEST_URL", type: "varchar2(255 char)")

			column(name: "USER_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-71") {
		createTable(tableName: "SEARCH_AUTH_GROUP") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_AUTH_GPK")
			}

			column(name: "GROUP_CATEGORY", type: "varchar2(255 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-72") {
		createTable(tableName: "search_auth_group_member") {
			column(name: "AUTH_GROUP_ID", type: "number(19,0)")

			column(name: "AUTH_USER_ID", type: "number(19,0)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-73") {
		createTable(tableName: "SEARCH_AUTH_PRINCIPAL") {
			column(name: "ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_AUTH_PPK")
			}

			column(name: "DATE_CREATED", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(255 char)")

			column(name: "ENABLED", type: "number(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "PRINCIPAL_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "UNIQUE_ID", type: "varchar2(255 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-74") {
		createTable(tableName: "SEARCH_AUTH_SEC_OBJECT_ACCESS") {
			column(name: "AUTH_SEC_OBJ_ACCESS_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_AUTH_SPK")
			}

			column(name: "SECURE_ACCESS_LEVEL_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "AUTH_PRINCIPAL_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "SECURE_OBJECT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-75") {
		createTable(tableName: "SEARCH_AUTH_USER") {
			column(name: "ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_AUTH_UPK")
			}

			column(name: "affiliation", type: "varchar2(255 char)")

			column(name: "EMAIL", type: "varchar2(255 char)")

			column(name: "EMAIL_SHOW", type: "number(1,0)")

			column(name: "LOGIN_ATTEMPT_COUNT", type: "number(10,0)")

			column(name: "PASSWD", type: "varchar2(255 char)")

			column(name: "PASSWORD_EXPIRED", type: "char(1 char)")

			column(name: "phone", type: "varchar2(255 char)")

			column(name: "USER_REAL_NAME", type: "varchar2(255 char)")

			column(name: "USERNAME", type: "varchar2(255 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-76") {
		createTable(tableName: "SEARCH_AUTH_USER_SEC_ACCESS_V") {
			column(name: "SEARCH_AUTH_USER_SEC_ACCESS_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_AUTH_UPK")
			}

			column(name: "SEARCH_SEC_ACCESS_LEVEL_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "SEARCH_AUTH_USER_ID", type: "number(19,0)")

			column(name: "SEARCH_SECURE_OBJECT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-77") {
		createTable(tableName: "SEARCH_BIO_MKR_CORREL_FAST_MV") {
			column(name: "MV_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_BIO_MKPK")
			}

			column(name: "ASSO_BIO_MARKER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CORREL_TYPE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DOMAIN_OBJECT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "VALUE_METRIC", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-78") {
		createTable(tableName: "SEARCH_CUSTOM_FILTER") {
			column(name: "SEARCH_CUSTOM_FILTER_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_CUSTOMPK")
			}

			column(name: "DESCRIPTION", type: "varchar2(4000 char)")

			column(name: "NAME", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}

			column(name: "PRIVATE", type: "varchar2(1 char)") {
				constraints(nullable: "false")
			}

			column(name: "SEARCH_USER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-79") {
		createTable(tableName: "SEARCH_CUSTOM_FILTER_ITEM") {
			column(name: "SEARCH_CUSTOM_FILTER_ITEM_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_CUSTOMPK")
			}

			column(name: "BIO_DATA_TYPE", type: "varchar2(100 char)") {
				constraints(nullable: "false")
			}

			column(name: "SEARCH_CUSTOM_FILTER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "UNIQUE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-80") {
		createTable(tableName: "SEARCH_GENE_SIG_FILE_SCHEMA") {
			column(name: "SEARCH_GENE_SIG_FILE_SCHEMA_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_GENE_SPK")
			}

			column(name: "DESCRIPTION", type: "varchar2(255 char)")

			column(name: "NAME", type: "varchar2(100 char)") {
				constraints(nullable: "false")
			}

			column(name: "NUMBER_COLUMNS", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "SUPPORTED", type: "number(1,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-81") {
		createTable(tableName: "SEARCH_GENE_SIGNATURE") {
			column(name: "SEARCH_GENE_SIGNATURE_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_GENE_SPK")
			}

			column(name: "ANALYSIS_METHOD_CONCEPT_ID", type: "number(19,0)")

			column(name: "ANALYSIS_METHOD_OTHER", type: "varchar2(255 char)")

			column(name: "ANALYST_NAME", type: "varchar2(100 char)")

			column(name: "ANALYTIC_CAT_CONCEPT_ID", type: "number(19,0)")

			column(name: "ANALYTIC_CAT_OTHER", type: "varchar2(255 char)")

			column(name: "CREATED_BY_AUTH_USER_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CREATE_DATE", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "DELETED_FLAG", type: "number(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "varchar2(1000 char)")

			column(name: "EXPERIMENT_TYPE_ATCC_REF", type: "varchar2(255 char)")

			column(name: "EXPERIMENT_TYPE_CELL_LINE_ID", type: "number(19,0)")

			column(name: "EXPERIMENT_TYPE_CONCEPT_ID", type: "number(19,0)")

			column(name: "EXPERIMENT_TYPE_IN_VIVO_DESCR", type: "varchar2(255 char)")

			column(name: "SEARCH_GENE_SIG_FILE_SCHEMA_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "FOLD_CHG_METRIC_CONCEPT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_MODIFIED_DATE", type: "timestamp")

			column(name: "MODIFIED_BY_AUTH_USER_ID", type: "number(19,0)")

			column(name: "MULTIPLE_TESTING_CORRECTION", type: "number(19,0)")

			column(name: "NAME", type: "varchar2(100 char)") {
				constraints(nullable: "false")
			}

			column(name: "NORM_METHOD_CONCEPT_ID", type: "number(19,0)")

			column(name: "NORM_METHOD_OTHER", type: "varchar2(255 char)")

			column(name: "OWNER_CONCEPT_ID", type: "number(19,0)")

			column(name: "P_VALUE_CUTOFF_CONCEPT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "PARENT_GENE_SIGNATURE_ID", type: "number(19,0)")

			column(name: "PMID_LIST", type: "varchar2(255 char)")

			column(name: "PUBLIC_FLAG", type: "number(1,0)") {
				constraints(nullable: "false")
			}

			column(name: "SOURCE_CONCEPT_ID", type: "number(19,0)")

			column(name: "SOURCE_OTHER", type: "varchar2(255 char)")

			column(name: "SPECIES_CONCEPT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "SPECIES_MOUSE_DETAIL", type: "varchar2(255 char)")

			column(name: "SPECIES_MOUSE_SRC_CONCEPT_ID", type: "number(19,0)")

			column(name: "STIMULUS_DESCRIPTION", type: "varchar2(1000 char)")

			column(name: "STIMULUS_DOSING", type: "varchar2(255 char)")

			column(name: "BIO_ASSAY_PLATFORM_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "TISSUE_TYPE_CONCEPT_ID", type: "number(19,0)")

			column(name: "TREATMENT_BIO_COMPOUND_ID", type: "number(19,0)")

			column(name: "TREATMENT_DESCRIPTION", type: "varchar2(1000 char)")

			column(name: "TREATMENT_DOSING", type: "varchar2(255 char)")

			column(name: "TREATMENT_PROTOCOL_NUMBER", type: "varchar2(50 char)")

			column(name: "UNIQUE_ID", type: "varchar2(50 char)")

			column(name: "UPLOAD_FILE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "VERSION_NUMBER", type: "varchar2(50 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-82") {
		createTable(tableName: "SEARCH_GENE_SIGNATURE_ITEM") {
			column(name: "ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_GENE_SPK")
			}

			column(name: "BIO_DATA_UNIQUE_ID", type: "varchar2(255 char)")

			column(name: "BIO_MARKER_ID", type: "number(19,0)")

			column(name: "FOLD_CHG_METRIC", type: "double precision")

			column(name: "SEARCH_GENE_SIGNATURE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "BIO_ASSAY_FEATURE_GROUP_ID", type: "number(19,0)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-83") {
		createTable(tableName: "SEARCH_KEYWORD") {
			column(name: "SEARCH_KEYWORD_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_KEYWORPK")
			}

			column(name: "BIO_DATA_ID", type: "number(19,0)")

			column(name: "DATA_CATEGORY", type: "varchar2(400 char)")

			column(name: "SOURCE_CODE", type: "varchar2(200 char)")

			column(name: "DISPLAY_DATA_CATEGORY", type: "varchar2(400 char)")

			column(name: "KEYWORD", type: "varchar2(400 char)") {
				constraints(nullable: "false")
			}

			column(name: "OWNER_AUTH_USER_ID", type: "number(19,0)")

			column(name: "UNIQUE_ID", type: "varchar2(1000 char)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-84") {
		createTable(tableName: "SEARCH_KEYWORD_TERM") {
			column(name: "SEARCH_KEYWORD_TERM_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_KEYWORPK")
			}

			column(name: "KEYWORD_TERM", type: "varchar2(200 char)") {
				constraints(nullable: "false")
			}

			column(name: "OWNER_AUTH_USER_ID", type: "number(19,0)")

			column(name: "RANK", type: "number(19,0)")

			column(name: "SEARCH_KEYWORD_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "TERM_LENGTH", type: "number(19,0)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-85") {
		createTable(tableName: "SEARCH_REQUEST_MAP") {
			column(name: "ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_REQUESPK")
			}

			column(name: "VERSION", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "CONFIG_ATTRIBUTE", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "URL", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-86") {
		createTable(tableName: "SEARCH_ROLE") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_ROLEPK")
			}

			column(name: "version", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-87") {
		createTable(tableName: "search_role_auth_user") {
			column(name: "AUTHORITIES_ID", type: "number(19,0)")

			column(name: "PEOPLE_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-88") {
		createTable(tableName: "SEARCH_SEC_ACCESS_LEVEL") {
			column(name: "SEARCH_SEC_ACCESS_LEVEL_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_SEC_ACPK")
			}

			column(name: "ACCESS_LEVEL_NAME", type: "varchar2(400 char)")

			column(name: "ACCESS_LEVEL_VALUE", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-89") {
		createTable(tableName: "SEARCH_SECURE_OBJECT") {
			column(name: "SEARCH_SECURE_OBJECT_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_SECUREPK")
			}

			column(name: "BIO_DATA_ID", type: "number(19,0)")

			column(name: "BIO_DATA_UNIQUE_ID", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "DATA_TYPE", type: "varchar2(400 char)")

			column(name: "DISPLAY_NAME", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-90") {
		createTable(tableName: "SEARCH_SECURE_OBJECT_PATH") {
			column(name: "SEARCH_SECURE_OBJ_PATH_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_SECUREPK")
			}

			column(name: "I2B2_CONCEPT_PATH", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SEARCH_SECURE_OBJECT_ID", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-91") {
		createTable(tableName: "SEARCH_USER_FEEDBACK") {
			column(name: "SEARCH_USER_FEEDBACK_ID", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCH_USER_FPK")
			}

			column(name: "APP_VERSION", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "CREATE_DATE", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "FEEDBACK_TEXT", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "SEARCH_USER_ID", type: "number(19,0)")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-92") {
		createTable(tableName: "SEARCHAPP.PLUGIN") {
			column(name: "PLUGIN_SEQ", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCHAPP.PLUPK")
			}

			column(name: "active", type: "char(1 char)") {
				constraints(nullable: "false")
			}

			column(name: "default_link", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "form_link", type: "varchar2(255 char)")

			column(name: "form_page", type: "varchar2(255 char)")

			column(name: "has_form", type: "char(1 char)") {
				constraints(nullable: "false")
			}

			column(name: "has_modules", type: "char(1 char)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "plugin_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-93") {
		createTable(tableName: "SEARCHAPP.PLUGIN_MODULE") {
			column(name: "MODULE_SEQ", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "SEARCHAPP.PLUPK")
			}

			column(name: "version", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "active", type: "char(1 char)") {
				constraints(nullable: "false")
			}

			column(name: "category", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "form_link", type: "varchar2(255 char)")

			column(name: "form_page", type: "varchar2(255 char)")

			column(name: "has_form", type: "char(1 char)") {
				constraints(nullable: "false")
			}

			column(name: "module_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "params", type: "clob") {
				constraints(nullable: "false")
			}

			column(name: "PLUGIN_SEQ", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-94") {
		createTable(tableName: "snp_data_by_patient") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "snp_data_by_pPK")
			}

			column(name: "version", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "chrom", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "data_by_patient", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "patient_num", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "trial_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-95") {
		createTable(tableName: "snp_data_by_probe") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "snp_data_by_pPK")
			}

			column(name: "version", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "chrom", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "chrom_pos", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "data_array", type: "raw(255)") {
				constraints(nullable: "false")
			}

			column(name: "probe_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "probe_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "snp_data_by_probe_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "snp_info_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "snp_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "trial_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-96") {
		createTable(tableName: "snp_dataset") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "snp_datasetPK")
			}

			column(name: "version", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "concept_id", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "concept_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "dataset_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "paired_dataset_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "patient_gender", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "patient_num", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "platform_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "sample_type", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "subject_id", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "time_point", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "trial_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-97") {
		createTable(tableName: "snp_dataset_by_patient") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "snp_dataset_bPK")
			}

			column(name: "version", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "snp_dataset_id", type: "number(19,0)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-98") {
		createTable(tableName: "snp_dataset_by_patient_snp_data_by_chrom_map") {
			column(name: "snp_data_by_chrom_map", type: "number(19,0)")

			column(name: "snp_data_by_chrom_map_idx", type: "varchar2(255 char)")

			column(name: "snp_data_by_chrom_map_elt", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-99") {
		createTable(tableName: "snp_dataset_list_by_probe") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "snp_dataset_lPK")
			}

			column(name: "version", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "trial_name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-100") {
		createTable(tableName: "snp_dataset_list_by_probe_dataset_compact_location_map") {
			column(name: "dataset_compact_location_map", type: "number(19,0)")

			column(name: "dataset_compact_location_map_idx", type: "varchar2(255 char)")

			column(name: "dataset_compact_location_map_elt", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-101") {
		createTable(tableName: "snp_dataset_list_by_probe_snp_data_by_chrom_map") {
			column(name: "snp_data_by_chrom_map", type: "number(19,0)")

			column(name: "snp_data_by_chrom_map_idx", type: "varchar2(255 char)")

			column(name: "snp_data_by_chrom_map_elt", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-102") {
		createTable(tableName: "snp_dataset_list_by_probe_snp_dataset_by_subject_map") {
			column(name: "snp_dataset_by_subject_map", type: "number(19,0)")

			column(name: "snp_dataset_by_subject_map_idx", type: "varchar2(255 char)")

			column(name: "snp_dataset_by_subject_map_elt", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-103") {
		createTable(tableName: "snp_info") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "snp_infoPK")
			}

			column(name: "version", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "chrom", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}

			column(name: "chrom_pos", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-104") {
		createTable(tableName: "string_line_reader") {
			column(name: "id", type: "number(19,0)") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "string_line_rPK")
			}

			column(name: "version", type: "number(19,0)") {
				constraints(nullable: "false")
			}

			column(name: "len", type: "number(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "pos", type: "number(10,0)") {
				constraints(nullable: "false")
			}

			column(name: "str", type: "varchar2(255 char)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-105") {
		addPrimaryKey(columnNames: "BIO_MARKER_ID, BIO_ASSAY_FEATURE_GROUP_ID", constraintName: "BIO_ASSAY_DATPK", tableName: "BIO_ASSAY_DATA_ANNOTATION")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-106") {
		addPrimaryKey(columnNames: "BIO_COMPOUND_ID, BIO_DATA_ID", tableName: "bio_data_compound")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-107") {
		addPrimaryKey(columnNames: "BIO_DISEASE_ID, BIO_DATA_ID", tableName: "bio_data_disease")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-108") {
		addPrimaryKey(columnNames: "BIO_MARKER_ID, BIO_DATA_ID", tableName: "bio_data_omic_marker")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-109") {
		addPrimaryKey(columnNames: "BIO_TAXONOMY_ID, BIO_DATA_ID", tableName: "bio_data_taxonomy")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-217") {
		createIndex(indexName: "module_name_uniq_1450135542987", tableName: "SEARCHAPP.PLUGIN_MODULE", unique: "true") {
			column(name: "module_name")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-218") {
		createIndex(indexName: "authority_uniq_1450135543003", tableName: "SEARCH_ROLE", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-219") {
		createSequence(sequenceName: "SEARCHAPP.PLUGIN_MODULE_SEQ")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-220") {
		createSequence(sequenceName: "SEARCHAPP.PLUGIN_SEQ")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-221") {
		createSequence(sequenceName: "SEQ_BIO_ASSAY_SAMPLE_ID")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-222") {
		createSequence(sequenceName: "SEQ_BIO_CONTENT_REPOSITORY_ID")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-223") {
		createSequence(sequenceName: "SEQ_BIO_DATA_FACT_ID")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-224") {
		createSequence(sequenceName: "SEQ_BIO_DATA_ID")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-225") {
		createSequence(sequenceName: "SEQ_BIO_PATIENT_EVENT_ATTRIBUTE_ID")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-226") {
		createSequence(sequenceName: "SEQ_BIO_PATIENT_EVENT_ID")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-227") {
		createSequence(sequenceName: "SEQ_I2B2_DATA_ID")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-228") {
		createSequence(sequenceName: "SEQ_SEARCH_DATA_ID")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-229") {
		createSequence(sequenceName: "hibernate_sequence")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-110") {
		addForeignKeyConstraint(baseColumnNames: "EXPERIMENT_ID", baseTableName: "BIO_ASSAY", constraintName: "FKD1BE0842E7F81D60", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-111") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASY_ANALYSIS_PLTFM_ID", baseTableName: "BIO_ASSAY_ANALYSIS", constraintName: "FK8175763987FDD115", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASY_ANALYSIS_PLTFM_ID", referencedTableName: "BIO_ASY_ANALYSIS_PLTFM", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-112") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_ANALYSIS_ID", baseTableName: "BIO_ASSAY_ANALYSIS_DATA", constraintName: "FKE22325F093A105B4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_ANALYSIS_ID", referencedTableName: "BIO_ASSAY_ANALYSIS", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-113") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", baseTableName: "BIO_ASSAY_ANALYSIS_DATA", constraintName: "FKE22325F01C657781", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", referencedTableName: "BIO_ASSAY_FEATURE_GROUP", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-114") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_PLATFORM_ID", baseTableName: "BIO_ASSAY_ANALYSIS_DATA", constraintName: "FKE22325F05CDD8A54", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_PLATFORM_ID", referencedTableName: "BIO_ASSAY_PLATFORM", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-115") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_ASSAY_ANALYSIS_DATA", constraintName: "FKE22325F01543FE69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-116") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_ANALYSIS_ID", baseTableName: "BIO_ASSAY_ANALYSIS_DATA_TEA", constraintName: "FK2F6B212193A105B4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_ANALYSIS_ID", referencedTableName: "BIO_ASSAY_ANALYSIS", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-117") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", baseTableName: "BIO_ASSAY_ANALYSIS_DATA_TEA", constraintName: "FK2F6B21211C657781", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", referencedTableName: "BIO_ASSAY_FEATURE_GROUP", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-118") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_PLATFORM_ID", baseTableName: "BIO_ASSAY_ANALYSIS_DATA_TEA", constraintName: "FK2F6B21215CDD8A54", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_PLATFORM_ID", referencedTableName: "BIO_ASSAY_PLATFORM", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-119") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_ASSAY_ANALYSIS_DATA_TEA", constraintName: "FK2F6B21211543FE69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-120") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_ASSAY_DATA", constraintName: "FKB1C171871543FE69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-121") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", baseTableName: "BIO_ASSAY_DATA_ANNOTATION", constraintName: "FKD56450E71C657781", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", referencedTableName: "BIO_ASSAY_FEATURE_GROUP", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-122") {
		addForeignKeyConstraint(baseColumnNames: "BIO_MARKER_ID", baseTableName: "BIO_ASSAY_DATA_ANNOTATION", constraintName: "FKD56450E7ACECE6A5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_MARKER_ID", referencedTableName: "BIO_MARKER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-123") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_DATASET_ID", baseTableName: "BIO_ASSAY_DATA_STATS", constraintName: "FKC9671FC7EDAD9600", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_DATASET_ID", referencedTableName: "BIO_ASSAY_DATASET", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-124") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", baseTableName: "BIO_ASSAY_DATA_STATS", constraintName: "FKC9671FC71C657781", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", referencedTableName: "BIO_ASSAY_FEATURE_GROUP", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-125") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_ASSAY_DATA_STATS", constraintName: "FKC9671FC71543FE69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-126") {
		addForeignKeyConstraint(baseColumnNames: "BIO_SAMPLE_ID", baseTableName: "BIO_ASSAY_DATA_STATS", constraintName: "FKC9671FC7312830A5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_SAMPLE_ID", referencedTableName: "BIO_SAMPLE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-127") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_ID", baseTableName: "BIO_ASSAY_DATASET", constraintName: "FK9E3B8D5BAE398A8F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_ID", referencedTableName: "BIO_ASSAY", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-128") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_ASSAY_DATASET", constraintName: "FK9E3B8D5B1543FE69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-129") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_ANALYSIS_ID", baseTableName: "bio_asy_analysis_dataset", constraintName: "FK658BEBA493A105B4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_ANALYSIS_ID", referencedTableName: "BIO_ASSAY_ANALYSIS", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-130") {
		addForeignKeyConstraint(baseColumnNames: "bio_assay_dataset_id", baseTableName: "bio_asy_analysis_dataset", constraintName: "FK658BEBA4EDAD9600", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_DATASET_ID", referencedTableName: "BIO_ASSAY_DATASET", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-131") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_CLINICAL_TRIAL_PT_GROUP", constraintName: "FK8B55C3E2ACB81EE3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_CLINICAL_TRIAL", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-132") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_CLINICAL_TRIAL_TIME_POINT", constraintName: "FKDDC2017CACB81EE3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_CLINICAL_TRIAL", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-133") {
		addForeignKeyConstraint(baseColumnNames: "REPOSITORY_ID", baseTableName: "BIO_CONTENT", constraintName: "FKBD1800023EEBEF4D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONTENT_REPO_ID", referencedTableName: "BIO_CONTENT_REPOSITORY", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-134") {
		addForeignKeyConstraint(baseColumnNames: "BIO_CONTENT_ID", baseTableName: "BIO_CONTENT_REFERENCE", constraintName: "FK7F15AF2EB30C73AB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_FILE_CONTENT_ID", referencedTableName: "BIO_CONTENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-135") {
		addForeignKeyConstraint(baseColumnNames: "BIO_CONTENT_REFERENCE_ID", baseTableName: "BIO_CONTENT_REFERENCE", constraintName: "FK376D1F8ED954FE84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONTENT_REFERENCE_ID", referencedTableName: "BIO_CONTENT_REFERENCE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-136") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "BIO_CONTENT_REFERENCE", constraintName: "FK376D1F8E316C6A5C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-137") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "BIO_CONTENT_REFERENCE", constraintName: "FK376D1F8E62137A2C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_ANALYSIS_ID", referencedTableName: "BIO_ASSAY_ANALYSIS", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-138") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "BIO_CONTENT_REFERENCE", constraintName: "FK376D1F8ECE8363B0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_ID", referencedTableName: "BIO_DATA_LITERATURE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-139") {
		addForeignKeyConstraint(baseColumnNames: "BIO_COMPOUND_ID", baseTableName: "bio_data_compound", constraintName: "FK2BEAEBA9956E1F69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_COMPOUND_ID", referencedTableName: "BIO_COMPOUND", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-140") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_compound", constraintName: "FK2BEAEBA9162DAA7A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_DATA_ID", referencedTableName: "BIO_ASSAY_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-141") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_compound", constraintName: "FK2BEAEBA9316C6A5C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-142") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_compound", constraintName: "FK2BEAEBA9CE8363B0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_ID", referencedTableName: "BIO_DATA_LITERATURE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-143") {
		addForeignKeyConstraint(baseColumnNames: "ASSO_BIO_DATA_ID", baseTableName: "BIO_DATA_CORRELATION", constraintName: "FK53064AC42846F2C4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_MARKER_ID", referencedTableName: "BIO_MARKER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-144") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_CORREL_DESCR_ID", baseTableName: "BIO_DATA_CORRELATION", constraintName: "FK1DD38EC4DED8A952", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_CORREL_DESCR_ID", referencedTableName: "BIO_DATA_CORREL_DESCR", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-145") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_CORREL_ID", baseTableName: "BIO_DATA_CORRELATION", constraintName: "FK53064AC43629D55F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_CORREL_ID", referencedTableName: "BIO_DATA_CORRELATION", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-146") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "BIO_DATA_CORRELATION", constraintName: "FK53064AC496136375", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_MARKER_ID", referencedTableName: "BIO_MARKER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-147") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_disease", constraintName: "FKE21097BE162DAA7A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_DATA_ID", referencedTableName: "BIO_ASSAY_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-148") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_disease", constraintName: "FKE21097BE316C6A5C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-149") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_disease", constraintName: "FKE21097BECE8363B0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_ID", referencedTableName: "BIO_DATA_LITERATURE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-150") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DISEASE_ID", baseTableName: "bio_data_disease", constraintName: "FKE21097BE3FF8B0CB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DISEASE_ID", referencedTableName: "BIO_DISEASE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-151") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_EXT_CODE_ID", baseTableName: "BIO_DATA_EXT_CODE", constraintName: "FKE2C22B49D082FA15", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_EXT_CODE_ID", referencedTableName: "BIO_DATA_EXT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-152") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "BIO_DATA_EXT_CODE", constraintName: "FKE2C22B49E07FBF65", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_ID", referencedTableName: "BIO_DATA_UID", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-153") {
		addForeignKeyConstraint(baseColumnNames: "BIO_LIT_REF_DATA_ID", baseTableName: "BIO_DATA_LITERATURE", constraintName: "FK118E27AF2679D403", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_REF_DATA_ID", referencedTableName: "BIO_LIT_REF_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-154") {
		addForeignKeyConstraint(baseColumnNames: "bio_assay_data_statistics_id", baseTableName: "bio_data_omic_marker", constraintName: "FKDCE0D423EE8D25A3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_DATA_STATS_ID", referencedTableName: "BIO_ASSAY_DATA_STATS", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-155") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_omic_marker", constraintName: "FKDCE0D423162DAA7A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_DATA_ID", referencedTableName: "BIO_ASSAY_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-156") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_omic_marker", constraintName: "FKDCE0D42349F15C76", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASY_ANALYSIS_DATA_ID", referencedTableName: "BIO_ASSAY_ANALYSIS_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-157") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_omic_marker", constraintName: "FKDCE0D423CE8363B0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_ID", referencedTableName: "BIO_DATA_LITERATURE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-158") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_omic_marker", constraintName: "FKDCE0D423EAE6AD8C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASY_ANALYSIS_DATA_ID", referencedTableName: "BIO_ASSAY_ANALYSIS_DATA_TEA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-159") {
		addForeignKeyConstraint(baseColumnNames: "BIO_MARKER_ID", baseTableName: "bio_data_omic_marker", constraintName: "FKDCE0D423ACECE6A5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_MARKER_ID", referencedTableName: "BIO_MARKER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-160") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "bio_data_taxonomy", constraintName: "FK3FFE554F316C6A5C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-161") {
		addForeignKeyConstraint(baseColumnNames: "BIO_TAXONOMY_ID", baseTableName: "bio_data_taxonomy", constraintName: "FK3FFE554FE894F5A9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_TAXONOMY_ID", referencedTableName: "BIO_TAXONOMY", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-162") {
		addForeignKeyConstraint(baseColumnNames: "BIO_DATA_ID", baseTableName: "BIO_DATA_UID", constraintName: "FK5FB3DB92316C6A5C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-163") {
		addForeignKeyConstraint(baseColumnNames: "bio_data_id", baseTableName: "BIO_DATA_UID", constraintName: "FK5FB3DB92E07FBF65", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_DATA_ID", referencedTableName: "BIO_DATA_UID", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-164") {
		addForeignKeyConstraint(baseColumnNames: "IN_VITRO_MODEL_ID", baseTableName: "BIO_LIT_ALT_DATA", constraintName: "FK91E7335FA34BBB68", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_MODEL_DATA_ID", referencedTableName: "BIO_LIT_MODEL_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-165") {
		addForeignKeyConstraint(baseColumnNames: "IN_VIVO_MODEL_ID", baseTableName: "BIO_LIT_ALT_DATA", constraintName: "FK91E7335F3A4C78A6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_MODEL_DATA_ID", referencedTableName: "BIO_LIT_MODEL_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-166") {
		addForeignKeyConstraint(baseColumnNames: "BIO_LIT_ALT_DATA_ID", baseTableName: "BIO_LIT_AMD_DATA", constraintName: "FK4072645017B8B53B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_ALT_DATA_ID", referencedTableName: "BIO_LIT_ALT_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-167") {
		addForeignKeyConstraint(baseColumnNames: "BIO_LIT_AMD_DATA_ID", baseTableName: "BIO_LIT_AMD_DATA", constraintName: "FK407264505E4B6CF2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_AMD_DATA_ID", referencedTableName: "BIO_LIT_AMD_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-168") {
		addForeignKeyConstraint(baseColumnNames: "IN_VITRO_MODEL_ID", baseTableName: "BIO_LIT_INT_DATA", constraintName: "FK3ABD10D9A34BBB68", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_MODEL_DATA_ID", referencedTableName: "BIO_LIT_MODEL_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-169") {
		addForeignKeyConstraint(baseColumnNames: "IN_VIVO_MODEL_ID", baseTableName: "BIO_LIT_INT_DATA", constraintName: "FK3ABD10D93A4C78A6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_MODEL_DATA_ID", referencedTableName: "BIO_LIT_MODEL_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-170") {
		addForeignKeyConstraint(baseColumnNames: "IN_VITRO_MODEL_ID", baseTableName: "BIO_LIT_PE_DATA", constraintName: "FK1B113115A34BBB68", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_MODEL_DATA_ID", referencedTableName: "BIO_LIT_MODEL_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-171") {
		addForeignKeyConstraint(baseColumnNames: "IN_VIVO_MODEL_ID", baseTableName: "BIO_LIT_PE_DATA", constraintName: "FK1B1131153A4C78A6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_LIT_MODEL_DATA_ID", referencedTableName: "BIO_LIT_MODEL_DATA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-172") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_ANALYSIS_ID", baseTableName: "BIO_MARKER_EXP_ANALYSIS_MV", constraintName: "FKA20E9DC93A105B4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_ANALYSIS_ID", referencedTableName: "BIO_ASSAY_ANALYSIS", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-173") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_MARKER_EXP_ANALYSIS_MV", constraintName: "FKA20E9DC1543FE69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-174") {
		addForeignKeyConstraint(baseColumnNames: "BIO_MARKER_ID", baseTableName: "BIO_MARKER_EXP_ANALYSIS_MV", constraintName: "FKA20E9DCACECE6A5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_MARKER_ID", referencedTableName: "BIO_MARKER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-175") {
		addForeignKeyConstraint(baseColumnNames: "BIO_CELL_LINE_ID", baseTableName: "BIO_SAMPLE", constraintName: "FK83BA10E1F29A83FC", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CELL_LINE_ID", referencedTableName: "BIO_CELL_LINE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-176") {
		addForeignKeyConstraint(baseColumnNames: "BIO_SUBJECT_ID", baseTableName: "BIO_SAMPLE", constraintName: "FK83BA10E1EAA2A06F", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_SUBJECT_ID", referencedTableName: "BIO_SUBJECT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-177") {
		addForeignKeyConstraint(baseColumnNames: "EXPERIMENT_ID", baseTableName: "BIO_SAMPLE", constraintName: "FK83BA10E1E7F81D60", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-178") {
		addForeignKeyConstraint(baseColumnNames: "BIO_EXPERIMENT_ID", baseTableName: "BIO_STATS_EXP_MARKER", constraintName: "FK5DAB92F31543FE69", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_EXPERIMENT_ID", referencedTableName: "BIO_EXPERIMENT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-179") {
		addForeignKeyConstraint(baseColumnNames: "BIO_MARKER_ID", baseTableName: "BIO_STATS_EXP_MARKER", constraintName: "FK5DAB92F3ACECE6A5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_MARKER_ID", referencedTableName: "BIO_MARKER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-180") {
		addForeignKeyConstraint(baseColumnNames: "PATH", baseTableName: "I2B2_TAGS", constraintName: "FK8F8DE59F75F653E7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "C_FULLNAME", referencedTableName: "I2B2", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-181") {
		addForeignKeyConstraint(baseColumnNames: "AUTH_GROUP_ID", baseTableName: "search_auth_group_member", constraintName: "FKAF5146BAD8C4BF40", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "SEARCH_AUTH_GROUP", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-182") {
		addForeignKeyConstraint(baseColumnNames: "AUTH_USER_ID", baseTableName: "search_auth_group_member", constraintName: "FKAF5146BA28B86E91", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SEARCH_AUTH_USER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-183") {
		addForeignKeyConstraint(baseColumnNames: "AUTH_PRINCIPAL_ID", baseTableName: "SEARCH_AUTH_SEC_OBJECT_ACCESS", constraintName: "FK4F0A70767767AE6B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SEARCH_AUTH_PRINCIPAL", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-184") {
		addForeignKeyConstraint(baseColumnNames: "SECURE_ACCESS_LEVEL_ID", baseTableName: "SEARCH_AUTH_SEC_OBJECT_ACCESS", constraintName: "FK4F0A70765ECC67D2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_SEC_ACCESS_LEVEL_ID", referencedTableName: "SEARCH_SEC_ACCESS_LEVEL", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-185") {
		addForeignKeyConstraint(baseColumnNames: "SECURE_OBJECT_ID", baseTableName: "SEARCH_AUTH_SEC_OBJECT_ACCESS", constraintName: "FK4F0A7076C28DC909", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_SECURE_OBJECT_ID", referencedTableName: "SEARCH_SECURE_OBJECT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-186") {
		addForeignKeyConstraint(baseColumnNames: "SEARCH_AUTH_USER_ID", baseTableName: "SEARCH_AUTH_USER_SEC_ACCESS_V", constraintName: "FKD7E29A1D21B5E3C8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SEARCH_AUTH_USER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-187") {
		addForeignKeyConstraint(baseColumnNames: "SEARCH_SEC_ACCESS_LEVEL_ID", baseTableName: "SEARCH_AUTH_USER_SEC_ACCESS_V", constraintName: "FKD7E29A1DC7FF6395", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_SEC_ACCESS_LEVEL_ID", referencedTableName: "SEARCH_SEC_ACCESS_LEVEL", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-188") {
		addForeignKeyConstraint(baseColumnNames: "SEARCH_SECURE_OBJECT_ID", baseTableName: "SEARCH_AUTH_USER_SEC_ACCESS_V", constraintName: "FKD7E29A1D68CFCAC0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_SECURE_OBJECT_ID", referencedTableName: "SEARCH_SECURE_OBJECT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-189") {
		addForeignKeyConstraint(baseColumnNames: "SEARCH_CUSTOM_FILTER_ID", baseTableName: "SEARCH_CUSTOM_FILTER_ITEM", constraintName: "FKEAF48FA3716B69A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_CUSTOM_FILTER_ID", referencedTableName: "SEARCH_CUSTOM_FILTER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-190") {
		addForeignKeyConstraint(baseColumnNames: "ANALYSIS_METHOD_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F6528FE323C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-191") {
		addForeignKeyConstraint(baseColumnNames: "ANALYTIC_CAT_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F659C5716BC", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-192") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_PLATFORM_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F655CDD8A54", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_PLATFORM_ID", referencedTableName: "BIO_ASSAY_PLATFORM", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-193") {
		addForeignKeyConstraint(baseColumnNames: "CREATED_BY_AUTH_USER_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F651ECFD622", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SEARCH_AUTH_USER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-194") {
		addForeignKeyConstraint(baseColumnNames: "EXPERIMENT_TYPE_CELL_LINE_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F65CDD2B348", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CELL_LINE_ID", referencedTableName: "BIO_CELL_LINE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-195") {
		addForeignKeyConstraint(baseColumnNames: "EXPERIMENT_TYPE_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F65B04C2CA4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-196") {
		addForeignKeyConstraint(baseColumnNames: "FOLD_CHG_METRIC_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F656E14D495", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-197") {
		addForeignKeyConstraint(baseColumnNames: "MODIFIED_BY_AUTH_USER_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F656CB77803", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SEARCH_AUTH_USER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-198") {
		addForeignKeyConstraint(baseColumnNames: "NORM_METHOD_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F6537F0371C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-199") {
		addForeignKeyConstraint(baseColumnNames: "OWNER_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F6535247BCD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-200") {
		addForeignKeyConstraint(baseColumnNames: "P_VALUE_CUTOFF_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F65DFD37036", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-201") {
		addForeignKeyConstraint(baseColumnNames: "PARENT_GENE_SIGNATURE_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F656C36A1D4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_GENE_SIGNATURE_ID", referencedTableName: "SEARCH_GENE_SIGNATURE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-202") {
		addForeignKeyConstraint(baseColumnNames: "SEARCH_GENE_SIG_FILE_SCHEMA_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F65EC8407D1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_GENE_SIG_FILE_SCHEMA_ID", referencedTableName: "SEARCH_GENE_SIG_FILE_SCHEMA", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-203") {
		addForeignKeyConstraint(baseColumnNames: "SOURCE_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F654281565", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-204") {
		addForeignKeyConstraint(baseColumnNames: "SPECIES_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F659683EDA4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-205") {
		addForeignKeyConstraint(baseColumnNames: "SPECIES_MOUSE_SRC_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F65C30B61F9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-206") {
		addForeignKeyConstraint(baseColumnNames: "TISSUE_TYPE_CONCEPT_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F65D8DB518C", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_CONCEPT_CODE_ID", referencedTableName: "BIO_CONCEPT_CODE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-207") {
		addForeignKeyConstraint(baseColumnNames: "TREATMENT_BIO_COMPOUND_ID", baseTableName: "SEARCH_GENE_SIGNATURE", constraintName: "FK8E0A6F652478FDA2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_COMPOUND_ID", referencedTableName: "BIO_COMPOUND", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-208") {
		addForeignKeyConstraint(baseColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", baseTableName: "SEARCH_GENE_SIGNATURE_ITEM", constraintName: "FKC3D993AD1C657781", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_ASSAY_FEATURE_GROUP_ID", referencedTableName: "BIO_ASSAY_FEATURE_GROUP", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-209") {
		addForeignKeyConstraint(baseColumnNames: "BIO_MARKER_ID", baseTableName: "SEARCH_GENE_SIGNATURE_ITEM", constraintName: "FKC3D993ADACECE6A5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "BIO_MARKER_ID", referencedTableName: "BIO_MARKER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-210") {
		addForeignKeyConstraint(baseColumnNames: "SEARCH_GENE_SIGNATURE_ID", baseTableName: "SEARCH_GENE_SIGNATURE_ITEM", constraintName: "FKC3D993ADA5F3FB52", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_GENE_SIGNATURE_ID", referencedTableName: "SEARCH_GENE_SIGNATURE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-211") {
		addForeignKeyConstraint(baseColumnNames: "SEARCH_KEYWORD_ID", baseTableName: "SEARCH_KEYWORD_TERM", constraintName: "FK1FF80259C0466363", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_KEYWORD_ID", referencedTableName: "SEARCH_KEYWORD", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-212") {
		addForeignKeyConstraint(baseColumnNames: "AUTHORITIES_ID", baseTableName: "search_role_auth_user", constraintName: "FKAA89F2F09048A312", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SEARCH_AUTH_USER", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-213") {
		addForeignKeyConstraint(baseColumnNames: "PEOPLE_ID", baseTableName: "search_role_auth_user", constraintName: "FKAA89F2F02B3AEC7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "SEARCH_ROLE", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-214") {
		addForeignKeyConstraint(baseColumnNames: "SEARCH_SECURE_OBJECT_ID", baseTableName: "SEARCH_SECURE_OBJECT_PATH", constraintName: "FK84A15C5468CFCAC0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "SEARCH_SECURE_OBJECT_ID", referencedTableName: "SEARCH_SECURE_OBJECT", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-215") {
		addForeignKeyConstraint(baseColumnNames: "PLUGIN_SEQ", baseTableName: "SEARCHAPP.PLUGIN_MODULE", constraintName: "FK8DF9132316AC454E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "PLUGIN_SEQ", referencedTableName: "SEARCHAPP.PLUGIN", referencesUniqueColumn: "false")
	}

	changeSet(author: "gabor (generated)", id: "1450135543115-216") {
		addForeignKeyConstraint(baseColumnNames: "snp_dataset_id", baseTableName: "snp_dataset_by_patient", constraintName: "FK5FF3604EB0A70304", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "snp_dataset", referencesUniqueColumn: "false")
	}
}
