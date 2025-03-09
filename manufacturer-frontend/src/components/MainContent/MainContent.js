// components/MainContent/MainContent.js
import React, { useState } from 'react';
import styles from './MainContent.module.css';
import { MusicService } from '../../api/musicService';

function MainContent() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleStart = async () => {
    try {
      setLoading(true);
      const response = await MusicService.startSession({
        date: '2024-05-05'
      });
      console.log('Session started:', response);
    } catch (err) {
      setError('서버 연결에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  const handleLearnMore = async () => {
    const response = await MusicService.getInfo();
    console.log('Info:', response);
  };

  const handlePreview = async () => {
    const response = await MusicService.getPreview(123);
    console.log('Preview:', response);
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>HSU Blocker</h1>
      <h2 className={styles.title}>Blockchain IoT Software Update Framework</h2>

      <div className={styles.platforms}>
        <h1>Deploying Software Update!</h1>
      </div>
      
      <div className={styles.buttonGroup}>
        <button 
          onClick={handleStart}
          disabled={loading}
          className={styles.buttonPrimary}
        >
          {loading ? '처리 중...' : 'Deploy Update'}
        </button>
        
        <button 
          onClick={handleLearnMore}
          className={styles.buttonSecondary}
        >
          Monitoring Update
        </button>
        
        <button 
          onClick={handlePreview}
          className={styles.buttonOutline}
        >
          Learn More
        </button>
      </div>

      {error && <div className={styles.error}>{error}</div>}
    </div>
  );
}

export default MainContent;