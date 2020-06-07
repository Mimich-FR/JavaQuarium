/*
 * This file is part of JavaQuarium, licensed under the MIT License.
 *
 *  Copyright (c)  Mimich
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.mimich.javaquarium.aquarium.fish;

public final class Fish implements IFish{

    private final String name;

    private final boolean female;

    private final FishType fishType;

    private int hp;


    public Fish(final String name, final boolean female, final FishType fishType) {

        this.name = name;

        this.female = female;

        this.fishType = fishType;

        this.hp = 10;

    }
    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final boolean isFemale() {

        return this.female;

    }

    @Override
    public final FishType getFishType() {

        return this.fishType;

    }

    @Override
    public int getHp() {

        return this.hp;

    }

    @Override
    public void setHp(int hp) {

        this.hp = hp;

    }


}
