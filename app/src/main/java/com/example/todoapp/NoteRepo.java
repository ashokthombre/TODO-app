package com.example.todoapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {

    private NoteDao noteDao;

    private LiveData<List<Notes>> notelist;

    public  NoteRepo(Application application)
    {
        NotesDatabase notesDatabase=NotesDatabase.getInstance(application);

        noteDao=notesDatabase.noteDao();

        notelist=noteDao.getAll();
    }
    public void insertData(Notes notes)
    {
       new InsertTask(noteDao).execute(notes);
    }
    public  void updateData(Notes notes)
    {
        new UpdateTask(noteDao).execute(notes);
    }
    public  void deleteData(Notes notes)
    {
        new DeleteTask(noteDao).execute(notes);
    }

    public LiveData<List<Notes>>getAll()
    {
        return notelist;
    }

    private static class InsertTask extends AsyncTask<Notes,Void,Void>
    {
        private NoteDao noteDao;

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {

            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Notes,Void,Void>
    {
        private NoteDao noteDao;

        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {

            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class UpdateTask extends AsyncTask<Notes,Void,Void>
    {
        private NoteDao noteDao;

        public UpdateTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Notes... notes) {

            noteDao.update(notes[0]);
            return null;
        }
    }

}
