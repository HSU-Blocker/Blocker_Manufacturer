// components/MainContent/MainContent.js
import styles from './MainContent.module.css';

function MainContent() {
  return (
    <div className={styles.container}>
      <h2 className={styles.subtitle}>Ready to make some noise?</h2>
      <h1 className={styles.title}>Let's start making music on May 5.</h1>
      
      <div className={styles.buttonGroup}>
        <button className={styles.buttonPrimary}>Start Now</button>
        <button className={styles.buttonSecondary}>Learn More</button>
        <button className={styles.buttonOutline}>Preview</button>
      </div>

      <div className={styles.platforms}>
        <p>Soon on the</p>
        <h3>My Music</h3>
        <p>Soon on</p>
        <h3>Google Play</h3>
      </div>
    </div>
  );
}

export default MainContent;