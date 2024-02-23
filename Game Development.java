import pygame
import random

# Initialize pygame
pygame.init()

# Set screen dimensions
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600

# Define colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)

# Set up the screen
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("Simple Game")

# Set up game variables
player_size = 50
player_x = SCREEN_WIDTH // 2 - player_size // 2
player_y = SCREEN_HEIGHT - 2 * player_size
player_speed = 10

enemy_size = 50
enemy_x = random.randint(0, SCREEN_WIDTH - enemy_size)
enemy_y = -enemy_size
enemy_speed = 5

score = 0
font = pygame.font.SysFont(None, 40)

# Main game loop
running = True
clock = pygame.time.Clock()

while running:
    # Handle events
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Move player
    keys = pygame.key.get_pressed()
    if keys[pygame.K_LEFT] and player_x > 0:
        player_x -= player_speed
    if keys[pygame.K_RIGHT] and player_x < SCREEN_WIDTH - player_size:
        player_x += player_speed

    # Move enemy
    enemy_y += enemy_speed
    if enemy_y > SCREEN_HEIGHT:
        enemy_x = random.randint(0, SCREEN_WIDTH - enemy_size)
        enemy_y = -enemy_size
        score += 1

    # Check for collision
    if player_x < enemy_x + enemy_size and player_x + player_size > enemy_x \
            and player_y < enemy_y + enemy_size and player_y + player_size > enemy_y:
        running = False

    # Fill the screen with white color
    screen.fill(WHITE)

    # Draw player
    pygame.draw.rect(screen, BLACK, (player_x, player_y, player_size, player_size))

    # Draw enemy
    pygame.draw.rect(screen, BLACK, (enemy_x, enemy_y, enemy_size, enemy_size))

    # Display score
    text = font.render("Score: " + str(score), True, BLACK)
    screen.blit(text, (10, 10))

    # Update the display
    pygame.display.update()

    # Set frame rate
    clock.tick(30)

# Game over
screen.fill(WHITE)
font = pygame.font.SysFont(None, 80)
text = font.render("Game Over", True, BLACK)
screen.blit(text, (SCREEN_WIDTH // 2 - 200, SCREEN_HEIGHT // 2 - 40))
text = font.render("Score: " + str(score), True, BLACK)
screen.blit(text, (SCREEN_WIDTH // 2 - 120, SCREEN_HEIGHT // 2 + 40))
pygame.display.update()

# Keep the window open until closed by the user
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            quit()
