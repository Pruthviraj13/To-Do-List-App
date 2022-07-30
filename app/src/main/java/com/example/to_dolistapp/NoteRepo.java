package com.example.to_dolistapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;


public class NoteRepo {
    private NotesDao notesDao;
    private LiveData<List<Notes>> notelist;
    public NoteRepo(Application application){
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        notesDao=noteDatabase.notesDao();
        notelist=notesDao.getAllData();
    }

    public void insertData(Notes notes){
        new InsertTask(notesDao).execute(notes);

    }
    public void updateData(Notes notes){
        new UpdateTask(notesDao).execute(notes);
    }
    public void deleteData(Notes notes){
        new DeleteTask(notesDao).execute(notes);
    }

    public LiveData<List<Notes>> getAllData(){
        return notelist;
    }
    private static class InsertTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao notesDao;

        public InsertTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao notesDao;

        public UpdateTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDao.update(notes[0]);
            return null;
        }
    }

     static class DeleteTask extends AsyncTask<Notes,Void,Void>{
        private NotesDao notesDao;

        public DeleteTask(NotesDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDao.delete(notes[0]);
            return null;
        }
    }
}
