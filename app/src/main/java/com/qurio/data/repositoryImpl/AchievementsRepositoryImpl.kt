package com.qurio.data.repositoryImpl

import com.qurio.R
import com.qurio.data.local.dao.AchievementsDao
import com.qurio.data.local.entity.AchievementsEntity
import com.qurio.data.repository.AchievementsRepository
import jakarta.inject.Inject

class AchievementsRepositoryImpl @Inject constructor(
    private val dao: AchievementsDao
) : AchievementsRepository {
    override suspend fun initAchievements() {
        if (dao.getCount() == 0) {
            val defaultAchievements = listOf(
                AchievementsEntity(
                    id = 1,
                    title = "Quiz Rookie",
                    description = "Welcome to the world of trivia! Every expert starts as a beginner",
                    lockedImage = R.drawable.quiz_rookie_locked,
                    ownedImage = R.drawable.quiz_rookie_owned,
                    howToGetIt = "Complete your first quiz",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 2,
                    title = "Streak Starter",
                    description = "You're heating up! Once you start winning, you just can't stop",
                    lockedImage = R.drawable.streak_starter_locked,
                    ownedImage = R.drawable.streak_starter_owned,
                    howToGetIt = "Answer 5 questions correctly in a row",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 3,
                    title = "Lucky Guess",
                    description = "Sometimes luck is all you need! You guessed it right against the odds",
                    lockedImage = R.drawable.lucky_guess_locked,
                    ownedImage = R.drawable.lucky_guess_owned,
                    howToGetIt = "Answer a question correctly after choosing randomly (less than 5 seconds of thinking time)",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 4,
                    title = "Explorer",
                    description = "You love experimenting and discovering! You've tried several categories and aren't limited to just one",
                    lockedImage = R.drawable.explorer_locked,
                    ownedImage = R.drawable.explorer_owned,
                    howToGetIt = "Play in at least 4 different categories",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 5,
                    title = "Trivia Champ",
                    description = "You're a true trivia master! Knowledge runs in your veins",
                    lockedImage = R.drawable.trivia_champ_locked,
                    ownedImage = R.drawable.trivia_champ_owned,
                    howToGetIt = "Win 10 full quizzes",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 6,
                    title = "Collector",
                    description = "You're building quite the trophy shelf! Keep earning those achievements",
                    lockedImage = R.drawable.collector_locked,
                    ownedImage = R.drawable.collector_owned,
                    howToGetIt = "Unlock 5 different achievements",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 7,
                    title = "Legend",
                    description = "Few can reach your level. Your name echoes through trivia history",
                    lockedImage = R.drawable.legend_locked,
                    ownedImage = R.drawable.legend_owned,
                    howToGetIt = "Achieve all other achievements in the game",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 8,
                    title = "Untouchable",
                    description = "You rarely make mistakes! You're at a very high level and dominate the game",
                    lockedImage = R.drawable.untouchable_locked,
                    ownedImage = R.drawable.untouchable_owned,
                    howToGetIt = "Correctly answer 10 consecutive questions in a single game without making a single mistake",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 9,
                    title = "Quick Thinker",
                    description = "Your mind works faster than lightning! No hesitation, just pure instinct",
                    lockedImage = R.drawable.quick_thinker_locked,
                    ownedImage = R.drawable.quick_thinker_owned,
                    howToGetIt = "Answer 3 questions correctly in less than 3 seconds each",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 10,
                    title = "Collector II",
                    description = "You've gone beyond collecting — now you're mastering the art of achievement hunting!",
                    lockedImage = R.drawable.collector_ii_locked,
                    ownedImage = R.drawable.collector_ii_owned,
                    howToGetIt = "Unlock 10 different achievements",
                    isAchieved = false
                ),
                AchievementsEntity(
                    id = 11,
                    title = "Lucky Guess II",
                    description = "Luck truly favors you! Even the hardest questions can't stop your intuition",
                    lockedImage = R.drawable.lucky_guess_ii_locked,
                    ownedImage = R.drawable.lucky_guess_ii_owned,
                    howToGetIt = "Answer 5 questions correctly in less than 5 seconds each within one game",
                    isAchieved = false
                )
            )

            dao.inertAll(defaultAchievements)
        }
    }

    override suspend fun getAchievements(): List<AchievementsEntity> {
        return dao.getAchievements()
    }
}